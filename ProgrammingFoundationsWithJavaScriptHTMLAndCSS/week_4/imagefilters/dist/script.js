var can = null;
var img = null;
var file = null;
var output = null;
function uploadImage(){
  can = document.getElementById("c1");
  file = document.getElementById("idfile");
  img = new SimpleImage(file);
  img.drawTo(can);
}

function imageIsLoaded(images){
  if (images == null || !images.complete()){
    alert("Please upload an image.")
    return false;
  } else{
    return true;
  }
}

function doGrey(){
  for (var pixel of img.values()){
    var red = pixel.getRed();
    var green = pixel.getGreen();
    var blue = pixel.getBlue();
    var newValue = (red+green+blue)/3
    pixel.setRed(newValue);
    pixel.setBlue(newValue);
    pixel.setGreen(newValue);
  }
}

function makeGreyScale(){
  if (imageIsLoaded(img)){
    doGrey();
    img.drawTo(can);
  }
}

function doRed(){
  for (var pixel of img.values()){
    var avg = (pixel.getRed()+pixel.getGreen()+pixel.getBlue())/3;
    if (avg < 128){
      pixel.setRed(2*avg);
      pixel.setGreen(0);
      pixel.setBlue(0);
    } else{
      pixel.setRed(255);
      pixel.setGreen(2*avg-255);
      pixel.setBlue(2*avg-255);
    }
  }
}

function makeRed(){
  if(imageIsLoaded(img)){
    doRed();
    img.drawTo(can);
  }
}

function sineFormula(x,Ampli){
  var ans = Math.sin(1*x*Math.PI/180)*Ampli;
  return ans;
}

function doSine(shift,Ampli){
  var h = img.getHeight();
  for (var pixel of img.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    if (y-shift<=sineFormula(x,Ampli)||(y-(h-shift))>=-sineFormula(x,Ampli)){
      pixel.setRed(86);
      pixel.setGreen(120);
      pixel.setBlue(214);
    }
  }
}

function makeSine(){
  if (imageIsLoaded(img)){
    doSine(60,36);
    img.drawTo(can);
  }
}

function changeP(p,r,g,b){
  var avg = (p.getRed()+p.getGreen()+p.getBlue())/3;
  p.setRed(r*avg);
  p.setGreen(g*avg);
  p.setBlue(b*avg);
}

function doRainBow(){
  var rb = img.getHeight()/7;
  for (var pixel of img.values()){
    var y = pixel.getY();
    if (y<=rb){
      changeP(pixel,2,0,0);
    } else if (y<=2*rb){
      changeP(pixel,2,0.8,0);
    } else if (y<=3*rb){
      changeP(pixel,2,2,0);
    } else if (y<=4*rb){
      changeP(pixel,0,2,0);
    } else if (y<=5*rb){
      changeP(pixel,0,0,2);
    } else if (y<=6*rb){
      changeP(pixel,0.8,0,2);
    } else {
      changeP(pixel,1.6,0,1.6);
    }
  }
}

function makeRainbow(){
  if (imageIsLoaded(img)){
    doRainBow();
    img.drawTo(can);
  }
}

function randomPixel(x,y,xc,yc){
  xd = Math.floor(Math.random()*xc)+1;
  yd = Math.floor(Math.random()*yc)+1;
  var X = x-xd;
  var Y = y-yd;
  if (X<0){
    X = x+xd;
  }
  if (Y<0){
    Y = y+yd;
  }
  return img.getPixel(X,Y);
}

function doBlur(){
  var w = img.getWidth();
  var h = img.getHeight();
  var xc = Math.floor(w*0.1)+1;
  var yc = Math.floor(h*0.12)+1;
  output = new SimpleImage(w,h);
  for (var pixel of output.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    if (Math.random()<0.5){
      output.setPixel(x,y,img.getPixel(x,y));
    } else {
      output.setPixel(x,y,randomPixel(x,y,xc,yc));
    }
  }
}

function makeBlur(){
  if (imageIsLoaded(img)){
    doBlur();
    output.drawTo(can);
  }
}

function resetImg(){
  if(imageIsLoaded(img)){
    img = new SimpleImage(file);
    img.drawTo(can);
  }
}