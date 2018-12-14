// --- Affichage et défilement du fond d'écran -----------------------------------------------------------------------------------------------------------------------------------------
void fond (){
  image (fond,0,yf);
  image (fond,0,yf-height);
  if (yf >= height) {yf = yf-height;}
  yf=yf+1;
}

// --- Création d'une nouvelle vague d'ennemis -----------------------------------------------------------------------------------------------------------------------------------------
void nouvelle_Vague(){
  while (idEnnemi<=99){
      xEnnemi[idEnnemi] = random(425);     
      yEnnemi[idEnnemi] = random(-10000,-1000);
      viee[idEnnemi] = 1;
      idEnnemi+=1 ;

  }
return;
}

// --- Mod developeur -----------------------------------------------------------------------------------------------------------------------------------------
void debugMod (){
  fill (255);
  text ("Vitesse="+ (vitesse),10,20);
  text ("Vie:"+vie,10,40);
}

// --- Tir -----------------------------------------------------------------------------------------------------------------------------------------
void tir(){       
  if (yTir[1]<=-100){ xTir[1]=xJoueur+25; yTir[1]=yJoueur+20;}
  if (yTir[2]<=-100){ xTir[2]=xJoueur-30; yTir[2]=yJoueur+20;}
  yTir[1] = yTir[1]-74;
  yTir[2] = yTir[2]-74;
  fill (#FF0303);rect(xTir[1],yTir[1],5,-50);
  fill (#FF0303);rect(xTir[2],yTir[2],5,-50);
return;
}
// --------------------------------------------------------------------------------------------------------------------------------------------
void ennemis(){
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
void finVague(){
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
void joueur(){
  image (joueur,xJoueur-width/8/2,yJoueur-height/10/2);
  if (xJoueur<mouseX){xJoueur=xJoueur+(mouseX-xJoueur)/10;}
  if (xJoueur>mouseX){xJoueur=xJoueur+(mouseX-xJoueur)/10;}
  if (yJoueur>mouseY){yJoueur=yJoueur+(mouseY-yJoueur)/10;}
  if (yJoueur<mouseY){yJoueur=yJoueur+(mouseY-yJoueur)/10;}
return;
}

// --------------------------------------------------------------------------------------------------------------------------------------------
void vie(){
  if (vie <= 0) {choix=0;nouvelle_Vague();}
  fill (#FF0000);rect (50,950,vie,30);
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
void menu (){

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




void pilotes() {
fond ();
image (degats,50,400);
image (vies,50,500);
image (maniabilite,50,600);
image (barre,0,700);


curseur();
return; 
}




// --------------------------------------------------------------------------------------------------------------------------------------------
void curseur(){
idCurseur=1;
if (mousePressed ){idCurseur=2;}
curseur = loadImage("data/Images/curseurs/curseur"+idCurseur+".png");
curseur.resize(50,50);image (curseur,mouseX-5,mouseY-5);
}
