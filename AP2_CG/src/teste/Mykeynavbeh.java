package teste;

import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;



import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.*;

public class Mykeynavbeh extends Applet implements KeyListener {

 private SimpleUniverse universe = null;
 private Canvas3D canvas = null;
 private TransformGroup viewtrans = null;

 private TransformGroup tg = null;
 private Transform3D t3d = null;
 private Transform3D t3dstep = new Transform3D();
 private Matrix4d matrix = new Matrix4d();

 private Robot r2 = null;
private MovingCar movimentoRobo;
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
  OrbitBehavior ob = new OrbitBehavior(canvas);
  ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
  universe.getViewingPlatform().setViewPlatformBehavior(ob);

  universe.addBranchGraph(scene);
 }

 private BranchGroup createSceneGraph() {
  


  Appearance greyApp = new Appearance();
  setToMyDefaultAppearance(greyApp,new Color3f(0.18f,0.30f,0.30f));
  
  float tamanhocaixa = 0.1f;
  
  Box caixa1 = new Box(tamanhocaixa,tamanhocaixa,tamanhocaixa,greyApp);
  
  Transform3D tfPlatform = new Transform3D();
  tfPlatform.setTranslation(new Vector3d(-4.5f,-0.15f,0.0f));
  TransformGroup tgPlatform = new TransformGroup(tfPlatform);
  tgPlatform.addChild(caixa1);
  
      
	 Appearance whiteApp = new Appearance();
      setToMyDefaultAppearance(whiteApp,new Color3f(1.0f,1.0f,1.0f));
      
Box chao = new Box(5.2f,0.001f,2.5f,whiteApp);
  
  Transform3D tfChao= new Transform3D();
  tfChao.setTranslation(new Vector3d(0.0f,-0.255f,0.0f));
  TransformGroup tgChao = new TransformGroup(tfChao);
  tgChao.addChild(chao);
  
  
  
Box parede= new Box(5.2f,0.4f,0.001f,greyApp);
  
  Transform3D tfParede= new Transform3D();
  tfParede.setTranslation(new Vector3d(0.0f,0f,-2.5f));
  TransformGroup tgParede = new TransformGroup(tfParede);
  tgParede.addChild(parede);
  
  
  
Box parede2= new Box(5.2f,0.4f,0.001f,greyApp);
  
  Transform3D tfParede2= new Transform3D();
  tfParede2.setTranslation(new Vector3d(0.0f,0f,2.5f));
  TransformGroup tgParede2 = new TransformGroup(tfParede2);
  tgParede2.addChild(parede2);
  
  
  
  
Box parede3= new Box(0.001f,0.4f,2.5f,greyApp);
  
  Transform3D tfParede3= new Transform3D();
  tfParede3.setTranslation(new Vector3d(5.2f,0f,0.0f));
  TransformGroup tgParede3 = new TransformGroup(tfParede3);
  tgParede3.addChild(parede3);
  
  
  
  
  
Box parede4= new Box(0.001f,0.4f,2.5f,greyApp);
  
  Transform3D tfParede4= new Transform3D();
  tfParede4.setTranslation(new Vector3d(-5.2f,0f,0.0f));
  TransformGroup tgParede4 = new TransformGroup(tfParede4);
  tgParede4.addChild(parede4);
  	      
  Appearance yellowApp = new Appearance();
  setToMyDefaultAppearance(yellowApp,new Color3f(1f,1f,0.0f));


  float trunkHeight = 0.4f;

 
  Cylinder cylinder = new Cylinder(0.05f,trunkHeight,yellowApp);

  Transform3D tfPlatformCylinder = new Transform3D();
  tfPlatformCylinder.setTranslation(new Vector3d(4.5f,0.0f,1.5f));
  TransformGroup tgCylinder = new TransformGroup(tfPlatformCylinder);
  tgCylinder.setCollidable(false);
  tgCylinder.addChild(cylinder);
  	      

  Cylinder cylinder2 = new Cylinder(0.05f,trunkHeight,yellowApp);
  
  Transform3D tfPlatformCylinder2 = new Transform3D();
  tfPlatformCylinder2.setTranslation(new Vector3d(4.5f,0.0f,-1.5f));
  TransformGroup tgCylinder2 = new TransformGroup(tfPlatformCylinder2);
  tgCylinder2.setCollidable(false);
  tgCylinder2.addChild(cylinder2);
  
  
Cylinder cylinder3 = new Cylinder(0.05f,trunkHeight,yellowApp);
  
  Transform3D tfPlatformCylinder3 = new Transform3D();
  tfPlatformCylinder3.setTranslation(new Vector3d(1.5f,0.0f,0.0f));
  TransformGroup tgCylinder3 = new TransformGroup(tfPlatformCylinder3);
  tgCylinder3.setCollidable(false);
  tgCylinder3.addChild(cylinder3);
  
  
  
Cylinder cylinder4 = new Cylinder(0.2f,trunkHeight/2,greyApp);
  
  Transform3D tfPlatformCylinder4 = new Transform3D();
  tfPlatformCylinder4.setTranslation(new Vector3d(0f,-0.15f,0.0f));
  TransformGroup tgCylinder4 = new TransformGroup(tfPlatformCylinder4);
  tgCylinder4.setCollidable(false);
  tgCylinder4.addChild(cylinder4);
  
  
Cylinder cylinder5 = new Cylinder(0.05f,trunkHeight,yellowApp);
  
  Transform3D tfPlatformCylinder5 = new Transform3D();
  tfPlatformCylinder5.setTranslation(new Vector3d(-1.5f,0.0f,0.0f));
  TransformGroup tgCylinder5 = new TransformGroup(tfPlatformCylinder5);
  tgCylinder5.setCollidable(false);
  tgCylinder5.addChild(cylinder5);    
  
  
Cylinder cylinder6 = new Cylinder(0.05f,trunkHeight,yellowApp);
  
  Transform3D tfPlatformCylinder6 = new Transform3D();
  tfPlatformCylinder6.setTranslation(new Vector3d(-4.5f,0.0f,1.5f));
  TransformGroup tgCylinder6 = new TransformGroup(tfPlatformCylinder6);
  tgCylinder6.setCollidable(false);
  tgCylinder6.addChild(cylinder6);   
  
Cylinder cylinder7 = new Cylinder(0.05f,trunkHeight,yellowApp);
  
  Transform3D tfPlatformCylinder7 = new Transform3D();
  tfPlatformCylinder7.setTranslation(new Vector3d(-4.5f,0.0f,-1.5f));
  TransformGroup tgCylinder7 = new TransformGroup(tfPlatformCylinder7);
  tgCylinder7.setCollidable(false);
  tgCylinder7.addChild(cylinder7);   
  
  
  
  
//  ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
//  Scene s = null;
//
//  try
//  {
//    s = f.load("src/Model/R2.obj");
//  }
//  catch (Exception e)
//  {
//    System.out.println("File loading failed:" + e);
//  }
//  
//  
//
//  //Generate a transformation group for the loaded object.
//  Transform3D tfObject = new Transform3D();
// 
//  tfObject.rotY(0.5*Math.PI);
//  tfObject.setTranslation(new Vector3d(1.0f, 0.0f, 1.0f));
//  tfObject.setScale(0.25);
//  
//  TransformGroup tgObject = new TransformGroup(tfObject);
//  tgObject.addChild(s.getSceneGroup());


  
  
  BranchGroup theScene = new BranchGroup();
  
  theScene.addChild(tgChao);
  theScene.addChild(tgParede);
  theScene.addChild(tgParede2);
  theScene.addChild(tgParede3);
  theScene.addChild(tgParede4);
  theScene.addChild(tgPlatform);
  theScene.addChild(tgCylinder);
  theScene.addChild(tgCylinder2);	     
  theScene.addChild(tgCylinder3);
  theScene.addChild(tgCylinder4);
  theScene.addChild(tgCylinder5);
  theScene.addChild(tgCylinder6);
  theScene.addChild(tgCylinder7);
  
  theScene.addChild(createCar());

  return theScene;
 }

 private BranchGroup createCar() {

  BranchGroup objRoot = new BranchGroup();
  tg = new TransformGroup();
  t3d = new Transform3D();

  //tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
  t3d.rotY(0.5*Math.PI);
  t3d.setTranslation(new Vector3d(0.0f, 0.0f, -0.0f));
  
  t3d.setScale(1.0);

  tg.setTransform(t3d);

  /*
   * VrmlLoader loader = new VrmlLoader(); Scene s = null;
   * 
   * try { s = loader.load("model/Tink71.wrl"); } catch (Exception e) {
   * System.err.println(e); System.exit(1); }
   * 
   * tg.addChild(s.getSceneGroup( ) );
   */

  r2 = new Robot("src/model/R2.obj");
  tg.addChild(r2.tg);
  //car.tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
movimentoRobo = new MovingCar(r2);

  tg.addChild(movimentoRobo);

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
 
 public static void setToMyDefaultAppearance(Appearance app, Color3f col)
 {
   app.setMaterial(new Material(col,col,col,col,120.0f));
 }


 public static void main(String[] args) {
  Mykeynavbeh applet = new Mykeynavbeh();
  Frame frame = new MainFrame(applet, 800, 600);
 }

 public void keyTyped(KeyEvent e) {
  char key = e.getKeyChar();

  if (key == 'a') {

	  
	  r2.carZ-=0.1;
	   
	  r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
	   
	   
	  r2.tg.setTransform(r2.t3d);
//   t3dstep.rotY(Math.PI / 32); 
//   
//   r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
//   r2.t3d.mul(t3dstep);
//  
//   r2.tg.setTransform(r2.t3d);

  }

  if (key == 'd') {

	  
	  r2.carZ+=0.1;
	   
	  r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
	  
	   
	  r2.tg.setTransform(r2.t3d);
//   t3dstep.rotY(-Math.PI / 32);
//   r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
//   r2.t3d.mul(t3dstep);
//  
//   r2.tg.setTransform(r2.t3d);

  }
  
  
  if (key == 'w') {

	  r2.carX+=0.1;
	   
	  r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
	   
	   
	  r2.tg.setTransform(r2.t3d);
	  
	  }
  if (key == 's') {

	  r2.carX-=0.1;
	   
	  r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
	   
	   
	  r2.tg.setTransform(r2.t3d);
	  
	  }

 }

 public void keyReleased(KeyEvent e) {
 }

 public void keyPressed(KeyEvent e) {
 }
 
 
 
class Robot {
	
	public TransformGroup tg = null;
	  public Transform3D t3d = null;
	  private Transform3D t3dstep = new Transform3D();	 
	  private float carX=0,carY=0,carZ=0;
	 

	  public Robot(String filename) {

	   //Revised:
	   tg = new TransformGroup();
	   t3d = new Transform3D();
	   carX=+1f;
	   carY=0;
	   carZ=-0.5f;
	 
	   t3d.setTranslation(new Vector3d(carX, carY, carZ));
	   t3d.setScale(0.25);
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
	 
	
	
}
	  



}



 class MovingCar extends Behavior {
	 public TransformGroup tg = null;
	  public Transform3D t3d = null;
  
  private Transform3D t3dstep = new Transform3D();
  private WakeupOnElapsedFrames wakeFrame = null;
  private WakeupOnElapsedTime wakeTime = null;
  
  
  public Robot robot;

  

  public MovingCar(Robot robot) {
	
	  
	this.robot = robot;
	  tg=robot.tg;
	  t3d = robot.t3d;
}

public void initialize() {
//   wakeFrame = new WakeupOnElapsedFrames(0);
	  wakeTime = new WakeupOnElapsedTime(1);
   wakeupOn(wakeTime);
  }

  public void processStimulus(Enumeration criteria) {
	 
	  
	  r2.carZ+=0.1;
	   
	  r2.t3d.setTranslation(new Vector3d(r2.carX, r2.carY, r2.carZ));
	   
	   
	  r2.tg.setTransform(r2.t3d);
	  
//   t3dstep.set(new Vector3d(-1.0f, 0.0, 0.0f));
//   robot.tg.getTransform(robot.t3d);
//   robot.t3d.mul(t3dstep);
//   robot.tg.setTransform(robot.t3d);
   
   wakeupOn(wakeTime);   
	  
	
	
  }
 }

}