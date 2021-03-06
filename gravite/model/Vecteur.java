package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vecteur {
	private DoubleProperty x = new SimpleDoubleProperty(0);//abscisse du vecteur
	private DoubleProperty y = new SimpleDoubleProperty(0);//ordonnee du vecteur
	private double magnitude=1;//
	private double cap=-1;
	//Constructeur
	public Vecteur() {
		this(0,0);
	}
	public Vecteur(Vecteur vecteur) {
		this(vecteur.getX(),vecteur.getY(),vecteur.magnitude);
		this.cap=vecteur.cap;
	}
	public Vecteur(double magnitude) {
		this();
		this.magnitude=magnitude;
	}
	public Vecteur(double x,double y) {
		this.x.set(x);
		this.y.set(y);
	}
	public Vecteur(double x,double y,double magnitude) {
		this(x,y);
		this.magnitude=magnitude;
	}
	//Getter
	public double getX() {
		return this.x.get();
	}
	public double getY() {
		return this.y.get();
	}
	public double getMagnitude() {
		if(cap>=0)if(magnitude>cap)return cap;
		return magnitude;
	}
	public double getX_Magnitude() {
		return getX()*getMagnitude();
	}
	public double getY_Magnitude() {
		return getY()*getMagnitude();
	}
	public DoubleProperty getXproperty() {//x property of vector
		return x;
	}
	public DoubleProperty getYproperty() {//y property of vector
		return y;
	}
	//Setter
	public Vecteur setX(double x) {//Set x
		return setVecteur(x,getY());
	}
	public Vecteur setY(double y) {//Set y
		return setVecteur(getX(),y);
	}
	public Vecteur setVecteur(double x,double y) {//Set x et y
		this.x.set(x);
		this.y.set(y);
		return convert();
	}
	public Vecteur setMagnitude(double valeur) {
		this.magnitude=Math.abs(valeur);
		return this;
	}
	public Vecteur setCap(double valeur) {
		cap=valeur;
		return this;
	}
	//Calculs numériques sur vecteur
	
	//Additions sur vecteurs
	public Vecteur addVecteur(double x,double y) {
		this.x.set(getX_Magnitude()+x);
		this.y.set(getY_Magnitude()+y);
		return convert();
	}
	public Vecteur addVecteur(Vecteur vecteur) {
		return addVecteur(vecteur.getX_Magnitude(),vecteur.getY_Magnitude());
	}
	//Soustractions sur vecteurs
	public Vecteur subVecteur(double x,double y) {
		return addVecteur(-x,-y);
	}
	public Vecteur subVecteur(Vecteur vecteur) {
		return subVecteur(vecteur.getX_Magnitude(),vecteur.getY_Magnitude());
	}
	//Multiplications sur vecteurs
	public Vecteur multiplyVecteur(double x) {//Applique la multiplication sur le vecteur courant
		return multiplyVecteur(x,x);
	}
	public Vecteur getNewMultiplyVecteur(double multiply) {//Crée une nouvelle instance de vecteur sur lequel on applique la multiplication
		return new Vecteur(this).multiplyVecteur(multiply);
	}
	public Vecteur multiplyVecteur(double x,double y) {
		this.x.set(getX_Magnitude()*x);
		this.y.set(getY_Magnitude()*y);
		return convert();
	}
	public Vecteur multiplyVecteur(Vecteur vecteur) {//
		return multiplyVecteur(vecteur.getX_Magnitude(),vecteur.getY_Magnitude());
	}
	//Image / Inverse d'un vecteur
	public Vecteur opposateXVecteur() {//Vecteur mirroir en x
		x.set(-x.get());
		return this;
	}
	public Vecteur opposateYVecteur() {//Vecteur mirroir en y
		y.set(-y.get());
		return this;
	}
	public Vecteur opposateVecteur() {//Vecteur mirroir en x et y
		return opposateXVecteur().opposateYVecteur();
	}
	//Normalizing vector, and modifying it's magnitude to keep it the "same"
	private Vecteur convert() {
		return setMagnitude(Math.pow(getX()*getX()+getY()*getY(), 0.5)).normaliser();
	}
	//Normalisation (longueur==1)
	public Vecteur normaliser() {//Ramène le vecteur sur des normes (rayon =1)
		double longueur=longueurVecteur();
		
		if(longueur!=0) {
			x.set(getX_Magnitude()/longueur);
			y.set(getY_Magnitude()/longueur);
		}
		return this;
	}
	//Longueur 
	public double longueurVecteur() {//Longueur absolue du vecteur
		return Math.pow(getX_Magnitude()*getX_Magnitude()+getY_Magnitude()*getY_Magnitude(), 0.5);
	}
	public String toString() {
		return "["+this.getX_Magnitude()+";"+this.getY_Magnitude()+"]";
	}

}
