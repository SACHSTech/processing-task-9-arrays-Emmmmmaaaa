import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] snowX = new float[15];
  float[] snowY = new float[15];
  int snowDiameter = 20; 

  float circleY = 200;
  float circleX = 200;
  int playerLives = 3; 

  // Declare blue player variables 
  boolean wPressed = false; 
  boolean aPressed = false; 
  boolean sPressed = false; 
  boolean dPressed = false; 

  // Declare snow speed veriables 
  boolean upPressed = false; 
  boolean downPressed = false; 

  boolean[] ballHideStatus; 


  public void settings() {
	// put your size call here
    size(400, 400);
    ballHideStatus = new boolean[snowX.length]; 
  }

  public void setup() {
    background(0);
    
    // determine Y value for circles 
    for (int i = 0; i < snowX.length; i++){
      snowY[i] = random(height);
      snowX[i] = random(width);
    }
  }

 
  public void draw() {
    background(0);

    // Draw snow 
    snow();
    playerLives();
    
    if (playerLives > 0){
      bluePlayerCircle(); 
    }
    if (wPressed){
      circleY --; 
    }
    if (sPressed){
      circleY ++; 
    }
    if (aPressed){
      circleX --; 
    }
    if (dPressed){
      circleX ++; 
    }

    mouseClicked();
  }

  public void snow(){
    for (int i = 0; i < snowX.length; i++){
      if (!ballHideStatus [i]){
        ellipse(snowX[i], snowY[i], 20, 20);

        if (dist(snowX[i], snowY[i], circleX, circleY)< 35){
          playerLives--;
          snowY[i] = 0; 
          snowX[i] = random(width);
          ballHideStatus[i] = true;
        }
      }
    }

    fill (225);
    for (int i = 0; i < snowX.length; i++){
      if (ballHideStatus[i])
      fill (255);
         ellipse (snowX[i], snowY[i], snowDiameter, snowDiameter);
      if (downPressed){
        snowY[i] += 5;
      } else if (upPressed){
        snowY[i] += 0.5;
      } else {
        snowY[i] += 2;
      }


      if (snowY[i] > height){
        snowY [i] = 0;
        ballHideStatus[i] = false; 
      }
    }
  }


  public void playerLives(){
    for (int i = 0; i < playerLives; i++){
      float x = width - 35 - i * 35;
      float y = 20;
      fill (235, 64, 52); 
      rect (x, y, 25, 25);
    }

    if (playerLives <= 0){
      background(255);
      textSize(50);
      fill(0);
      textAlign(CENTER,CENTER);
      text("You Lose", width/2, height/2);
    }
  }

  public void bluePlayerCircle(){
    fill (2, 131, 217); 
    ellipse(circleX, circleY, 20, 20);
  }

  public void keyPressed(){
    circleX = constrain (circleX, 0, width);
    circleY = constrain(circleY, 0, height); 
    if (keyCode == UP){
      upPressed = true;
    }
    else if (keyCode == DOWN){
      downPressed = true; 
    }

    if (key == 'w'){
      wPressed = true; 
    }
    if (key == 'a'){
      aPressed = true; 
    }
    if (key == 's'){
      sPressed = true;
    }
    if (key == 'd'){
      dPressed = true; 
    }
  }

  public void keyReleased(){
    if (keyCode == UP){
      upPressed = false;
    }
    else if (keyCode == DOWN){
      downPressed = false; 
    }

    if (key == 'w'){
      wPressed = false; 
    }
    if (key == 'a'){
      aPressed = false; 
    }
    if (key == 's'){
      sPressed = false;
    }
    if (key == 'd'){
      dPressed = false; 
    }
  }

  public void mouseClicked() {
    float clickRadius = 10; 
    for (int i = 0; i < snowX.length; i++){
      float distance = dist(snowX[i], snowY[i], mouseX, mouseY);
      if (distance < clickRadius&&mousePressed){
        ballHideStatus [i] = true;
      }
    }
  }
}

