package area1;

import java.awt.Color;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;


import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.vecmath.*;



import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.vp.*;




public class Ambiente extends JFrame {
	
	  public Canvas3D myCanvas3D;


	  public Ambiente()	  
	  {
	    //Mechanism for closing the window and ending the program.
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Default settings for the viewer parameters.
	    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());


	    //Construct the SimpleUniverse:
	    //First generate it using the Canvas.
	    SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);


	    //Default position of the viewer.
	    simpUniv.getViewingPlatform().setNominalViewingTransform();


	    //The scene is generated in this method.
	    createSceneGraph(simpUniv);


	    //Add some light to the scene.
	    addLight(simpUniv);


	    //The following three lines enable navigation through the scene using the mouse.
	    OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
	    ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
	    simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);


	    //Show the canvas/window.
	    setTitle("AP2");
	    setSize(700,700);
	    getContentPane().add("Center", myCanvas3D);
	    setVisible(true);

	  }
	
	  
	  public static void main(String[] args) {
		  Ambiente sa = new Ambiente();
	}
	
	 public void createSceneGraph(SimpleUniverse su)
	  {
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
	      tgCylinder.addChild(cylinder);
	      	      
	
	      Cylinder cylinder2 = new Cylinder(0.05f,trunkHeight,yellowApp);
	      
	      Transform3D tfPlatformCylinder2 = new Transform3D();
	      tfPlatformCylinder2.setTranslation(new Vector3d(4.5f,0.0f,-1.5f));
	      TransformGroup tgCylinder2 = new TransformGroup(tfPlatformCylinder2);
	      tgCylinder2.addChild(cylinder2);
	      
	      
  Cylinder cylinder3 = new Cylinder(0.05f,trunkHeight,yellowApp);
	      
	      Transform3D tfPlatformCylinder3 = new Transform3D();
	      tfPlatformCylinder3.setTranslation(new Vector3d(1.5f,0.0f,0.0f));
	      TransformGroup tgCylinder3 = new TransformGroup(tfPlatformCylinder3);
	      tgCylinder3.addChild(cylinder3);
	      
	      
	      
  Cylinder cylinder4 = new Cylinder(0.2f,trunkHeight/2,greyApp);
	      
	      Transform3D tfPlatformCylinder4 = new Transform3D();
	      tfPlatformCylinder4.setTranslation(new Vector3d(0f,-0.15f,0.0f));
	      TransformGroup tgCylinder4 = new TransformGroup(tfPlatformCylinder4);
	      tgCylinder4.addChild(cylinder4);
	      
	      
Cylinder cylinder5 = new Cylinder(0.05f,trunkHeight,yellowApp);
	      
	      Transform3D tfPlatformCylinder5 = new Transform3D();
	      tfPlatformCylinder5.setTranslation(new Vector3d(-1.5f,0.0f,0.0f));
	      TransformGroup tgCylinder5 = new TransformGroup(tfPlatformCylinder5);
	      tgCylinder5.addChild(cylinder5);    
	      
	      
Cylinder cylinder6 = new Cylinder(0.05f,trunkHeight,yellowApp);
	      
	      Transform3D tfPlatformCylinder6 = new Transform3D();
	      tfPlatformCylinder6.setTranslation(new Vector3d(-4.5f,0.0f,1.5f));
	      TransformGroup tgCylinder6 = new TransformGroup(tfPlatformCylinder6);
	      tgCylinder6.addChild(cylinder6);   
	      
Cylinder cylinder7 = new Cylinder(0.05f,trunkHeight,yellowApp);
	      
	      Transform3D tfPlatformCylinder7 = new Transform3D();
	      tfPlatformCylinder7.setTranslation(new Vector3d(-4.5f,0.0f,-1.5f));
	      TransformGroup tgCylinder7 = new TransformGroup(tfPlatformCylinder7);
	      tgCylinder7.addChild(cylinder7);   
	      
	      
	      
	      
	      ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
	      Scene s = null;

	      try
	      {
	        s = f.load("src/Model/R2.obj");
	      }
	      catch (Exception e)
	      {
	        System.out.println("File loading failed:" + e);
	      }
	      
	      

	      //Generate a transformation group for the loaded object.
	      Transform3D tfObject = new Transform3D();
	     
	      tfObject.rotY(0.5*Math.PI);
	      tfObject.setTranslation(new Vector3d(1.0f, 0.0f, 1.0f));
	      tfObject.setScale(0.25);
	      
	      TransformGroup tgObject = new TransformGroup(tfObject);
	      tgObject.addChild(s.getSceneGroup());


	      
	      
	      BranchGroup theScene = new BranchGroup();

	      theScene.addChild(tgObject);
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
	      
	      theScene.compile();

	      //Add the scene to the universe.
	      su.addBranchGraph(theScene);
	  }
	 
	  public void addLight(SimpleUniverse su)
	  {

	    BranchGroup bgLight = new BranchGroup();

	    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	    Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
	    Vector3f lightDir1  = new Vector3f(-1.0f,0.0f,-0.5f);
	    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
	    light1.setInfluencingBounds(bounds);

	    bgLight.addChild(light1);
	    su.addBranchGraph(bgLight);
	  }
	  
	  public static void setToMyDefaultAppearance(Appearance app, Color3f col)
	  {
	    app.setMaterial(new Material(col,col,col,col,120.0f));
	  }
	
}