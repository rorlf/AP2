import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import com.sun.j3d.utils.behaviors.vp.*;

import com.sun.j3d.utils.picking.*;
import com.sun.j3d.utils.picking.behaviors.*;

public class InteractionExample2 extends Frame implements ActionListener
{


  public Canvas3D myCanvas3D; //The canvas to be drawn on.
  public Button myButton;     //The exit button.



  public InteractionExample2()
  {
    myButton = new Button("Exit");
    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());




    //The objects to be shown on the canvas are created in this method.
    //BranchGroup theScene = new BranchGroup();

    BranchGroup theScene = createSceneGraph();






    theScene.compile();




    //Put everything together in a simple universe:
    //The canvas to be drawn on
    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

    //Move the viewer to a point so that the origin can be seen.
    simpUniv.getViewingPlatform().setNominalViewingTransform();


    //Use parallel instead of perspective projection
    //simpUniv.getViewer().getView().setProjectionPolicy(View.PARALLEL_PROJECTION);


    //Add the objects in the scene.
    simpUniv.addBranchGraph(theScene);


    //To make it look nicer, a little bit of light is recommended.
    addLight(simpUniv);


    //This allows us to use the mouse to move the viewing platform.
    //Note that we have to import com.sun.j3d.utils.behaviors.vp.*;
    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0));
    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);






    //Show the canvas
    setTitle("Initiating transforms");
    setSize(700,700);
    setLayout(new BorderLayout());
    add("Center", myCanvas3D);
    add("South",myButton);
    myButton.addActionListener(this);
    setVisible(true);


  }




  public static void main(String[] args)
  {
     InteractionExample2 interaction = new InteractionExample2();
  }





  //Here we construct the scene
  public BranchGroup createSceneGraph()
  {


    //Create a red appearance for the box.
    Color3f ambientColourFBox = new Color3f(0.0f,0.0f,0.0f);
    Color3f emissiveColourFBox = new Color3f(0.0f,0.0f,0.0f);
    Color3f diffuseColourFBox = new Color3f(0.5f,0.0f,0.0f);
    Color3f specularColourFBox = new Color3f(0.8f,0.0f,0.0f);
    float shininessFBox = 128.0f;

    Appearance fBoxApp = new Appearance();

    fBoxApp.setMaterial(new Material(ambientColourFBox,emissiveColourFBox,
                          diffuseColourFBox,specularColourFBox,shininessFBox));


    //Create the box.
    Box fBox = new Box(0.2f,0.2f,0.2f,fBoxApp);

    //Add some data to the box in order to identify it, when it is picked.
    fBox.setUserData("box");



    //Create a transformgroup for the rotation of the box.
    TransformGroup tgmBox = new TransformGroup();
    tgmBox.addChild(fBox);

    //Define the rotation.
    Transform3D rotationAxis = new Transform3D();

    Alpha boxAlpha = new Alpha(1,2000);

    //Set the starting time of the rotation to infinity.
    boxAlpha.setStartTime(Long.MAX_VALUE);

    RotationInterpolator boxRotation = new RotationInterpolator(boxAlpha,tgmBox,
                                             rotationAxis,0.0f,(float) Math.PI*2);

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    boxRotation.setSchedulingBounds(bounds);

    //Add the rotation to the corresponding transform group.
    tgmBox.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tgmBox.addChild(boxRotation);


    //Create a transformgroup for the positioning of the box and add the
    //transformgroup with the rotation.
    Transform3D tfFBox = new Transform3D();
    tfFBox.rotY(Math.PI/6);
    Transform3D rotationX = new Transform3D();
    rotationX.rotX(-Math.PI/5);
    tfFBox.mul(rotationX);
    TransformGroup tgFBox = new TransformGroup(tfFBox);

    tgFBox.addChild(tgmBox);


    //The same for the sphere:
    Color3f ambientColourBSphere = new Color3f(0.0f,0.0f,0.0f);
    Color3f emissiveColourBSphere = new Color3f(0.0f,0.0f,0.0f);
    Color3f diffuseColourBSphere = new Color3f(0.0f,0.5f,0.0f);
    Color3f specularColourBSphere = new Color3f(0.0f,0.8f,0.0f);
    float shininessBSphere = 128.0f;

    Appearance bSphereApp = new Appearance();

    bSphereApp.setMaterial(new Material(ambientColourBSphere,
                                        emissiveColourBSphere,
                                        diffuseColourBSphere,
                                        specularColourBSphere,
                                        shininessBSphere));

    Sphere bSphere = new Sphere(0.4f,bSphereApp);

    bSphere.setUserData("sphere");

    TransformGroup tgmBSphere = new TransformGroup();
    tgmBSphere.addChild(bSphere);


    //Create the transformation for shrinking.
    Alpha sphereShrinkAlpha = new Alpha(1,2000);
    sphereShrinkAlpha.setStartTime(Long.MAX_VALUE);

    ScaleInterpolator shrinker = new ScaleInterpolator(sphereShrinkAlpha,
                                                       tgmBSphere,
                                                       new Transform3D(),
                                                       1.0f,0.5f);

    shrinker.setSchedulingBounds(bounds);



    tgmBSphere.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tgmBSphere.addChild(shrinker);


    //Create the transformation for stretching the sphere again.
    Alpha sphereStretchAlpha = new Alpha(1,2000);
    sphereStretchAlpha.setStartTime(Long.MAX_VALUE);

    ScaleInterpolator stretcher = new ScaleInterpolator(sphereStretchAlpha,
                                                       tgmBSphere,
                                                       new Transform3D(),
                                                       0.5f,1.0f);

    stretcher.setSchedulingBounds(bounds);

    tgmBSphere.addChild(stretcher);



    //Create a transform group to position the sphere and add the
    //transfromgroup with the shrinking/stretching.
    Transform3D tfBSphere = new Transform3D();
    tfBSphere.setTranslation(new Vector3f(2.0f,0.0f,-10.5f));
    TransformGroup tgBSphere = new TransformGroup(tfBSphere);
    tgBSphere.addChild(tgmBSphere);

    //Create the root of the scene graph and add the box and the sphere group.
    BranchGroup theScene = new BranchGroup();;
    theScene.addChild(tgFBox);
    theScene.addChild(tgBSphere);


    //Define the data for the picking interaction and add the picking to the
    //scene graph.
    Alpha[] alphas = new Alpha[3];

    alphas[0] = boxAlpha;
    alphas[1] = sphereShrinkAlpha;
    alphas[2] = sphereStretchAlpha;

    BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    SimplePicking2 sp = new SimplePicking2(myCanvas3D,theScene,bs,alphas);

    theScene.addChild(sp);


    return(theScene);
  }






  //To make the scene look a little bit more realistic we add some light
  public void addLight(SimpleUniverse su)
  {

    BranchGroup bgLight = new BranchGroup();

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
    Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
    Vector3f lightDir1  = new Vector3f(-1.0f,0.0f,-0.5f);
    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
    light1.setInfluencingBounds(bounds);

    bgLight.addChild(light1);


    Vector3f lightDir2  = new Vector3f(0.0f,0.0f,-1.0f);
    DirectionalLight light2 = new DirectionalLight(lightColour1, lightDir2);
    light2.setInfluencingBounds(bounds);

    bgLight.addChild(light2);



    su.addBranchGraph(bgLight);

  }




  //Handles the exit button action to quit the program.
  public void actionPerformed(ActionEvent e)
  {
    dispose();
    System.exit(0);
  }




}
