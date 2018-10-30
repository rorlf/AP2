package teste;
import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;

import com.sun.j3d.loaders.Scene;
import com.mnstarfire.loaders3d.Loader3DS;

public class Mykeynavbeh extends Applet {

 private SimpleUniverse universe = null;
 private Canvas3D canvas = null;
 private TransformGroup viewtrans = null;

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

  objRoot.addChild(createShuttle2());

  return objRoot;
 }

 private BranchGroup createShuttle2() {

  BranchGroup objRoot = new BranchGroup();
  TransformGroup tg = new TransformGroup();
  Transform3D t3d = new Transform3D();

  tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

  t3d.setTranslation(new Vector3d(0.0, 0, 0.0));
  // t3d.setRotation(new AxisAngle4d(1, 0, 0, Math.toRadians(-90)));
  t3d.setScale(0.4);

  tg.setTransform(t3d);

  Loader3DS loader = new Loader3DS();
  Scene s = null;

  try {
   s = loader.load("Android.3ds");
  } catch (Exception e) {
   System.err.println(e);
   System.exit(1);
  }

  tg.addChild(s.getSceneGroup());

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
}