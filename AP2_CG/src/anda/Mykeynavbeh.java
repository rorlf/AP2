package anda;

import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

import org.jdesktop.j3d.loaders.vrml97.VrmlLoader;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import com.sun.j3d.utils.image.TextureLoader;

import java.util.*;

import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;

public class Mykeynavbeh extends Applet implements KeyListener {

 private SimpleUniverse universe = null;
 private Canvas3D canvas = null;
 private TransformGroup viewtrans = null;

 // private TransformGroup tg = null;
 // private Transform3D t3d = null;
 private Transform3D t3dstep = new Transform3D();
 private Matrix4d matrix = new Matrix4d();

 private MovingCar car = null;

 private float speed = -0.03f;
 private boolean cstate = false;

 public Mykeynavbeh() {
  setLayout(new BorderLayout());
  GraphicsConfiguration config = SimpleUniverse
    .getPreferredConfiguration();

  canvas = new Canvas3D(config);
  add("Center", canvas);
  universe = new SimpleUniverse(canvas);

  BranchGroup scene = createSceneGraph();
  universe.getViewingPlatform().setNominalViewingTransform();

  universe.getViewer().getView().setBackClipDistance(100.0);

  canvas.addKeyListener(this);

  universe.addBranchGraph(scene);
 }

 private BranchGroup createSceneGraph() {
  BranchGroup objRoot = new BranchGroup();

  BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

  viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

  KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
  keyNavBeh.setSchedulingBounds(bounds);
  PlatformGeometry platformGeom = new PlatformGeometry();
  platformGeom.addChild(keyNavBeh);
  universe.getViewingPlatform().setPlatformGeometry(platformGeom);

  objRoot.addChild(createCar());
  objRoot.addChild(createTrees0());

  Background background = new Background();
  background.setColor(1.0f, 1.0f, 1.0f);
  background.setApplicationBounds(bounds);
  objRoot.addChild(background);

  return objRoot;
 }

 private BranchGroup createCar() {

  BranchGroup objRoot = new BranchGroup();
  // tg = new TransformGroup();

  // tg_car = new TransformGroup();
  // t3d_car = new Transform3D();

  TransformGroup tg = new TransformGroup();

  TransformGroup tg_car = new TransformGroup();
  Transform3D t3d_car = new Transform3D();

  // tg_car.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

  t3d_car.setTranslation(new Vector3d(0.0, -1.5, -8.0));
  t3d_car.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, 0.35f));
  t3d_car.setScale(2.0);

  tg_car.setTransform(t3d_car);

  car = new MovingCar("src/model/R2.obj");
  tg_car.addChild(car.tg);
  // car.tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

  tg_car.addChild(car);

  BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
    1000.0);

  CollisionDetectorGroup cdGroup = new CollisionDetectorGroup(car.tg);
  cdGroup.setSchedulingBounds(bounds);

  tg.addChild(tg_car);
  tg.addChild(cdGroup);

  // objRoot.addChild(tg_car);
  objRoot.addChild(tg);
  objRoot.addChild(createLight());

  objRoot.compile();

  return objRoot;

 }

 

 private Light createLight() {
  DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f,
    1.0f, 1.0f), new Vector3f(-0.3f, 0.2f, -1.0f));

  light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

  return light;
 }

 public static void main(String[] args) {
  Mykeynavbeh applet = new Mykeynavbeh();
  Frame frame = new MainFrame(applet, 800, 600);
 }

 public void keyTyped(KeyEvent e) {
  char key = e.getKeyChar();

  if (key == 'd') {
   speed = -0.03f;

   cstate = true;
   car.setEnable(cstate);
  }

  if (key == 's') {

   t3dstep.rotY(Math.PI / 32);
   car.tg.getTransform(car.t3d);
   car.t3d.get(matrix);
   car.t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
   car.t3d.mul(t3dstep);
   car.t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13,
     matrix.m23));
   car.tg.setTransform(car.t3d);

  }

  if (key == 'f') {

   t3dstep.rotY(-Math.PI / 32);
   car.tg.getTransform(car.t3d);
   car.t3d.get(matrix);
   car.t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
   car.t3d.mul(t3dstep);
   car.t3d.setTranslation(new Vector3d(matrix.m03, matrix.m13,
     matrix.m23));
   car.tg.setTransform(car.t3d);

  }

  if (key == 'e') {
   speed -= 0.01f;
   System.out.println("speed: " + speed);
  }

  if (key == 'x') {
   cstate = false;
   car.setEnable(cstate);
  }

 }

 
 
 
 public void keyReleased(KeyEvent e) {
 }

 public void keyPressed(KeyEvent e) {
 }

 class CollisionDetectorGroup extends Behavior {
  private boolean inCollision = false;
  private Group group;

  private WakeupOnCollisionEntry wEnter;
  private WakeupOnCollisionExit wExit;

  public CollisionDetectorGroup(Group gp) { // Corrected: gp
   group = gp; // Corrected: gp
   inCollision = false;

  }

  public void initialize() {
   wEnter = new WakeupOnCollisionEntry(group);
   wExit = new WakeupOnCollisionExit(group);
   wakeupOn(wEnter);
  }

  public void processStimulus(Enumeration criteria) {

   inCollision = !inCollision;
   if (inCollision) {

    car.t3dstep.set(new Vector3d(0.0, 0.0, -speed * 5));
    car.tg.getTransform(car.t3d);
    car.t3d.mul(car.t3dstep);
    car.tg.setTransform(car.t3d);

    cstate = false;
    car.setEnable(cstate);

    wakeupOn(wExit);
   } else {
    wakeupOn(wEnter);
   }
  }
 }

 class MovingCar extends Behavior {

  public TransformGroup tg = null;
  public Transform3D t3d = null;
  private Transform3D t3dstep = new Transform3D();
  private WakeupOnElapsedFrames wakeFrame = null;
  public MovingCar(String filename) {
   
   //Revised:
   tg = new TransformGroup();
   t3d = new Transform3D();
 
   t3d.setTranslation(new Vector3d(0.0, 0.0, 0.0));
   t3d.setScale(1.0);
   tg.setTransform(t3d);
   //

   ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
   Scene s = null;

   try {
    s = f.load(filename);
   } catch (Exception e) {
    System.err.println(e);
    System.exit(1);
   }

   tg.addChild(s.getSceneGroup());

   tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

   BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0,
     0.0), 1000.0);
   this.setSchedulingBounds(bounds);
  }

  public void initialize() {
   wakeFrame = new WakeupOnElapsedFrames(0);
   wakeupOn(wakeFrame);
  }

  public void processStimulus(Enumeration criteria) {
   t3dstep.set(new Vector3d(0.0, 0.0, speed));
   tg.getTransform(t3d);
   t3d.mul(t3dstep);
   tg.setTransform(t3d);

   wakeupOn(wakeFrame);
  }
 }

}