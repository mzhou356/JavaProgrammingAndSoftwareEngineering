function alertchange(){
  alert("Thank you for clicking!");
}

function confirmchange(){
  var choice = confirm("Please choose OK or cancel.");
  var message = "Are you sure you want to cancel?";
  if (choice == true){
    message = "You pressed OK!";
  }
  alert(message);
}

function colorChange(){
  var dd1 = document.getElementById("id1");
  var dd2 = document.getElementById("id2");
  dd1.className = "darkblueback";
  dd1.style.color="orange";
  dd2.className = "darkgreyback";
  dd2.style.color="green";
}

function textChange(){
  var dd1 = document.getElementById("id1");
  var hh2 = document.getElementById("header2");
  var L1 = document.getElementById("list1");
  dd1.innerHTML = "Ciao a tutti!";
  hh2.innerHTML = "sezione uno";
  L1.innerHTML = "elenchi";
  document.getElementById("b1").value="cliccami";
  document.getElementById("b2").value="cambia colore";
  document.getElementById("b3").value="cambia le parole";
  document.getElementById("b4").value="il grigio";
  document.getElementById("b5").value="il verde giallo";
}

function doGrey(){
  var v1 = document.getElementById("c2");
  v1.style.backgroundColor="DimGray";
  var context = v1.getContext("2d");
  context.clearRect(80,10,v1.width,v1.height);
  v1.style.backgroundColor="PowderBlue";
}

function doGreenYellow(){
  var v2 = document.getElementById("c2");
  v2.style.backgroundColor="GreenYellow";
  var context=v2.getContext("2d");
  context.fillStyle="MidnightBlue";
  context.fillRect(80,10,60,60);
  context.beginPath();
  context.arc(180,40,30,0,2*Math.PI);
  context.closePath();
  context.fill();
  context.fillStyle="orange";
  context.font="20pt Arial";
  context.fillText("Ciao",80,40);
  context.fillStyle="Tomato";
  context.font="20pt Arial";
  context.fillText("Bye",160,40);
}

function docolor(){
  var can = document.getElementById("c3");
  var colorinput = document.getElementById("clr");
  var color = colorinput.value;
  can.style.backgroundColor=color;
}

function docolor2(){
  var can = document.getElementById("c4");
  var colorinput = document.getElementById("clr2");
  var color = colorinput.value;
  can.style.backgroundColor=color;
}

function dosquare(){
  var can = document.getElementById("c5");
  var sizeinput = document.getElementById("slr");
  var size = sizeinput.value;
  var context = can.getContext("2d");
  context.clearRect(0,0,can.width,can.height);
  var color = document.getElementById("clr").value;
  context.fillStyle=color;
  context.fillRect(0,0,size,size);
  context.fillRect(parseInt(size)+30,0,size*0.8,size*0.8);
  context.beginPath();
  context.arc(parseInt(size)+120,60,size,0,2*Math.PI);
  context.closePath();
  context.fill();
}

function upload(){
  var textinput=document.getElementById("tinput");
  var text = textinput.value;
  alert("Chose" + text);
}

function upload2(){
  var can = document.getElementById("c6");
  var fileinput=document.getElementById("finput");
  image= new SimpleImage(fileinput);  //global variable 
  image2 = new SimpleImage(fileinput);//grayscaleimage
  image.drawTo(can);
}

function makeGrayScale(){
  var can = document.getElementById("c7");
  for (var pixel of image2.values()){
    var red = pixel.getRed();
    var green = pixel.getGreen();
    var blue = pixel.getBlue();
    var newValue = (red+green+blue)/3
    pixel.setRed(newValue);
    pixel.setBlue(newValue);
    pixel.setGreen(newValue);
  }
  image2.drawTo(can);
}

var fgImage = null;

function loadForegroundImage(){
  var img = document.getElementById("fgfile");
  fgImage = new SimpleImage(img);
  var can = document.getElementById("c8");
  fgImage.drawTo(can);
}

var bgImage = null;

function loadbackgroundImage(){
  var img = document.getElementById("bgfile");
  bgImage = new SimpleImage(img);
  var can = document.getElementById("c9");
  bgImage.drawTo(can);
}

function clearCanvas(){
  var can1 = document.getElementById("c8");
  var can2 = document.getElementById("c9");
  var context = can1.getContext("2d");
  context.clearRect(0,0,can1.width,can1.height);
  var context2 = can2.getContext("2d");
  context2.clearRect(0,0,can2.width,can2.height);
}

function makeComposite(){
  if (fgImage == null || !fgImage.complete()){
    alert("foregraund image is not ready");
    return;
  }
  if (bgImage == null || !bgImage.complete()){
    alert("background image is not ready");
    return;
  }
  clearCanvas();
  var can = document.getElementById("c8");
  var output = 
      new SimpleImage(fgImage.getWidth(),fgImage.getHeight());
  for (var pixel of fgImage.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    if (pixel.getGreen()==255 && pixel.getRed()===0 && pixel.getBlue()===0){
      output.setPixel(x,y,bgImage.getPixel(x,y));
    }else{
      output.setPixel(x,y,pixel);
    }
  }
  output.drawTo(can);
}