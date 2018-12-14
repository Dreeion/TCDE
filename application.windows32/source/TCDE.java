import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TCDE extends PApplet {

/***********************************************************/
// Nom du programme : TCDE
// Auteur(s) : Dreion & Daeranil & Hupsy
// Date :12/12/2018
// Version :0.007
///***********************************************************/
// Description du programme :
//===========================
// Un jeu dans l'espace.
/***********************************************************/
 
 
/***********************************************************/
/******************* Entête déclarative ********************/
/***********************************************************/ 



// --- inclusion des librairies utilisées ---


// --- librairie sons ---
//ù






// --- déclaration objets ---
boolean a;
int xJoueur;
int yJoueur;
int vitesse = 15;//vitesse
int yf = 0;
int vie;
PImage joueur;
PImage fond;
PImage UI;
//--- menu ---
PImage jouer;
PImage skin;
PImage parametre;
PImage credit;
PImage quitter;
PImage update;
PImage don;
PImage curseur;
PImage Ennemi1;
PImage Ennemi2;
PImage Ennemi3;
PImage Ennemi4;
PImage Ennemi5;
PImage Ennemi;
//--- Pilotes --- 
PImage degats;
PImage vies;
PImage maniabilite;
PImage barre;

//--- ---
float fin;
float xEnnemi[]= new float[100];
float yEnnemi[]= new float[100];

float viee[]= new float[100];
float xTir[]= new float[50];
float yTir[]= new float[50];
int j = 10;
int p = 1;
int idCurseur;

 
 
 int idEnnemi = 1;
 int idTir = 1;
 int m;
 int choix = 0;
Minim minim;
AudioSnippet ping;

// --- déclaration variables globales ---

/********************* Fonction SETUP **********************/
// fonction d'initialisation exécutée 1 fois au démarrage
/***********************************************************/

public void setup ()
{  

  yTir[1]=-100000;
  // --- initialisation des musiques ---
  minim = new Minim(this);
  ping = minim.loadSnippet("Musique.wav");
  ping.play();
  
  // --- initialisation fenêtre de base ---
  
   // ouvre une fenêtre xpixels  x ypixels
  background(0); // couleur fond fenetre ( noir )
  noCursor();
  // ---- initialisation paramètres graphiques utilisés ---
  

  // --- attributions des variables images ---
  degats = loadImage("data/Images/Menu/button_degats.png");
  vies =loadImage("data/Images/Menu/button_vies.png");
  maniabilite =loadImage("data/Images/Menu/button_vitesse.png");
  barre =loadImage("data/Images/Menu/button (1).png");
  
  fond = loadImage("data/Images/fond/stars.jpg");               //Charger l'image du fond d'écran
  fond.resize(width,height);                               // Taille du fond d'écran 

  joueur = loadImage("data/Images/joueur/joueur"+j+".png");        //Charger l'image du vaisseau du joueur
  joueur.resize(width/8,height/10);                       //Taille du joueur

  Ennemi1 = loadImage("data/Images/Ennemi/Ennemi1.png");       //Charger l'image des ennemis
  Ennemi2 = loadImage("data/Images/Ennemi/Ennemi2.png");       //Charger l'image des ennemis
  Ennemi3 = loadImage("data/Images/Ennemi/Ennemi3.png");       //Charger l'image des ennemis
  Ennemi4 = loadImage("data/Images/Ennemi/Ennemi4.png");       //Charger l'image des ennemis
  Ennemi5 = loadImage("data/Images/Ennemi/Ennemi5.png");       //Charger l'image des ennemis
Ennemi1.resize(75,75);  
Ennemi2.resize(75,75);  
Ennemi3.resize(75,75);  
Ennemi4.resize(75,75);  
Ennemi5.resize(75,75);  

  UI = loadImage("data/Images/UI/UI.png");

  // --- attributions des variables numériques ---

  xJoueur=width/2-width/8/2;yJoueur=height-height/10*2; //Coordonnées de départ du joueur                                         // Vitesse des ennemis                                             //Point de vie de base du joueur                                                  //Coordonnée de départ du fond d'écran (pour le défilement verticale)
  

// --- définition des coordonnées des ennemis de la première vague

nouvelle_Vague();
      
}
/********************** Fonction DRAW **********************/
//             fonction exécutée en boucle
/***********************************************************/

public void  draw(){
  if (choix==0){
    menu();}
  if (choix==1){
    finVague ();
    fond ();
    ennemis ();
    joueur ();
    tir();
    vie();
    image (UI,0,850);  
    debugMod ();
}
  if (choix==2){pilotes();}
  if (choix==3){menu();}
  if (choix==4){menu();}
  if (choix==5){stop();}
  delay (3);
 }
/********************** Fonction STOP **********************/
//          fonction exécutée quand le programme est fermé
/***********************************************************/
public void stop () {
  ping.close();
  minim.stop();
  super.stop();
  exit();
  
  
}
// --- Affichage et défilement du fond d'écran -----------------------------------------------------------------------------------------------------------------------------------------
public void fond (){
  image (fond,0,yf);
  image (fond,0,yf-height);
  if (yf >= height) {yf = yf-height;}
  yf=yf+1;
}

// --- Création d'une nouvelle vague d'ennemis -----------------------------------------------------------------------------------------------------------------------------------------
public void nouvelle_Vague(){
  while (idEnnemi<=99){
      xEnnemi[idEnnemi] = random(425);     
      yEnnemi[idEnnemi] = random(-10000,-1000);
      viee[idEnnemi] = 1;
      idEnnemi+=1 ;

  }
return;
}

// --- Mod developeur -----------------------------------------------------------------------------------------------------------------------------------------
public void debugMod (){
  fill (255);
  text ("Vitesse="+ (vitesse),10,20);
  text ("Vie:"+vie,10,40);
}

// --- Tir -----------------------------------------------------------------------------------------------------------------------------------------
public void tir(){       
  if (yTir[1]<=-100){ xTir[1]=xJoueur+25; yTir[1]=yJoueur+20;}
  if (yTir[2]<=-100){ xTir[2]=xJoueur-30; yTir[2]=yJoueur+20;}
  yTir[1] = yTir[1]-74;
  yTir[2] = yTir[2]-74;
  fill (0xffFF0303);rect(xTir[1],yTir[1],5,-50);
  fill (0xffFF0303);rect(xTir[2],yTir[2],5,-50);
return;
}
// --------------------------------------------------------------------------------------------------------------------------------------------
public void ennemis(){
  while (idEnnemi<=99){
    if (yEnnemi[idEnnemi]>-100 && yEnnemi[idEnnemi]<1100) {
      if (viee[idEnnemi]==1) {image (Ennemi1,xEnnemi[idEnnemi],yEnnemi[idEnnemi]);}
      else if (viee[idEnnemi]==2) {image (Ennemi2,xEnnemi[idEnnemi],yEnnemi[idEnnemi]);}
      else if (viee[idEnnemi]==3) {image (Ennemi3,xEnnemi[idEnnemi],yEnnemi[idEnnemi]);}
      else if (viee[idEnnemi]==4) {image (Ennemi4,xEnnemi[idEnnemi],yEnnemi[idEnnemi]);}
      else {image (Ennemi5,xEnnemi[idEnnemi],yEnnemi[idEnnemi]);}
    }
    if (yEnnemi[idEnnemi]<1300) {yEnnemi[idEnnemi]=yEnnemi[idEnnemi]+vitesse/2;}
    if ((xTir[1]+5 >= xEnnemi[idEnnemi]) && (xTir[1]<= xEnnemi[idEnnemi]+105) && (yTir[1]+50>= yEnnemi[idEnnemi]) &&  (yTir[1]+50<= yEnnemi[idEnnemi]+100)) {
      viee[idEnnemi]=viee[idEnnemi]+1;xTir[1]=xTir[1]+500;}
    if ((xTir[2]+5 >= xEnnemi[idEnnemi]) && (xTir[2]<= xEnnemi[idEnnemi]+105) && (yTir[2]+50>= yEnnemi[idEnnemi]) &&  (yTir[2]+50<= yEnnemi[idEnnemi]+100)) {
      viee[idEnnemi]=viee[idEnnemi]+1;xTir[2]=xTir[2]+500;}
    if (viee[idEnnemi]>=6){viee[idEnnemi]=5;xEnnemi[idEnnemi]=1500;}
    if (xEnnemi[idEnnemi] >= xTir[1] && xEnnemi[idEnnemi]<=xTir[1]+5 && yEnnemi[idEnnemi]<=yTir[1]-50  && yEnnemi[idEnnemi]>=yTir[1]){xTir[1]=1000;xEnnemi[idEnnemi]=1000;}
    if (xEnnemi[idEnnemi] >= xTir[2] && xEnnemi[idEnnemi]<=xTir[2]+5 && yEnnemi[idEnnemi]+75<=yTir[1]+0  && yEnnemi[idEnnemi]>=yTir[1]){xTir[2]=1000;xEnnemi[idEnnemi]=1000;}
  idEnnemi=idEnnemi+1;
  } 
idEnnemi=1;
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------
public void finVague(){
  while (idEnnemi<=99){
    fin = fin + yEnnemi[idEnnemi];
    idEnnemi=idEnnemi+1;}
    idEnnemi=1;
  if (fin >= 129000) {idEnnemi=1;vitesse=vitesse+1;
    nouvelle_Vague(); 
}
  fin = 0;
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------
public void joueur(){
  image (joueur,xJoueur-width/8/2,yJoueur-height/10/2);
  if (xJoueur<mouseX){xJoueur=xJoueur+(mouseX-xJoueur)/10;}
  if (xJoueur>mouseX){xJoueur=xJoueur+(mouseX-xJoueur)/10;}
  if (yJoueur>mouseY){yJoueur=yJoueur+(mouseY-yJoueur)/10;}
  if (yJoueur<mouseY){yJoueur=yJoueur+(mouseY-yJoueur)/10;}
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------
public void vie(){
  if (vie <= 0) {choix=0;nouvelle_Vague();}
  fill (0xffFF0000);rect (50,950,vie,30);
  while (idEnnemi<=99){
    if ((xJoueur+width/8/2+1>= xEnnemi[idEnnemi]) && (xJoueur<= xEnnemi[idEnnemi]+105) && (yJoueur+height/10/2-5>= yEnnemi[idEnnemi]) &&  (yJoueur-10<= yEnnemi[idEnnemi]+100)) {
      vie=vie-10;viee[idEnnemi]=viee[idEnnemi]+1;
    }
    idEnnemi=idEnnemi+1;
  // --------------------------------------------------------------------------------------------------------------------------------------------

}
  idEnnemi=1;   
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------
public void menu (){

  fond ();
  
  jouer = loadImage("data/Images/Menu/button_jouer.png");
  skin = loadImage("data/Images/Menu/button_skin.png");
  parametre = loadImage("data/Images/Menu/button_parametre.png");
  quitter = loadImage("data/Images/Menu/button_quitter.png");
  update = loadImage("data/Images/Menu/button.png");
  credit = loadImage("data/Images/Menu/button_credit.png");
  //don = loadImage("data/Images/Menu/button_.png");
  
  
  if (mouseX>=100 && mouseX<=400 && mouseY>=300 && mouseY<=350){jouer = loadImage("data/Images/Menu/button_jouer (1).png");}
  if (mouseX>=100 && mouseX<=400 && mouseY>=400 && mouseY<=450){skin = loadImage("data/Images/Menu/button_pilotes.png");}
  if (mouseX>=100 && mouseX<=400 && mouseY>=500 && mouseY<=550){parametre = loadImage("data/Images/Menu/button_parametres (1).png");}
  if (mouseX>=100 && mouseX<=400 && mouseY>=600 && mouseY<=650){credit = loadImage("data/Images/Menu/button_credits.png");}
  if (mouseX>=100 && mouseX<=400 && mouseY>=700 && mouseY<=750){quitter = loadImage("data/Images/Menu/button_quitter (1).png");}
  
  image (jouer,100,300);
  image (skin,100,400);
  image (parametre,100,500);
  image(credit,100,600);
  image (quitter,100,700);
  image (update,100,950);
  curseur();
  
  if (mousePressed && mouseX>=100 && mouseX<=400 && mouseY>=300 && mouseY<=350) {choix=1;vie = 300;}
  if (mousePressed && mouseX>=100 && mouseX<=400 && mouseY>=400 && mouseY<=450) {choix=2;}
  if (mousePressed && mouseX>=100 && mouseX<=400 && mouseY>=500 && mouseY<=550) {choix=3;}
  if (mousePressed && mouseX>=100 && mouseX<=400 && mouseY>=600 && mouseY<=650) {choix=4;}
  if (mousePressed && mouseX>=100 && mouseX<=400 && mouseY>=700 && mouseY<=750) {choix=5;}
  
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------




public void pilotes() {
fond ();
image (degats,50,400);
image (vies,50,500);
image (maniabilite,50,600);
image (barre,0,700);


curseur();
return; 
}




// --------------------------------------------------------------------------------------------------------------------------------------------
public void curseur(){
idCurseur=1;
if (mousePressed ){idCurseur=2;}
curseur = loadImage("data/Images/curseurs/curseur"+idCurseur+".png");
curseur.resize(50,50);image (curseur,mouseX-5,mouseY-5);
}
  public void settings() {  size(500, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TCDE" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
