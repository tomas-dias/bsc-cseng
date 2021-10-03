/* Exercicio 1
function caminho(p){
	p.beginPath();
	p.moveTo(0, 0);
	p.arcTo(64, 0, 64, 64, 16);
	p.lineTo(64, 64);
	p.closePath();
	p.stroke();
}

function main() {
    var c2d = document.getElementById("exercicios").getContext("2d");
    caminho(c2d);
}
*/

/* Exercicio 2
function circulo(c){
	c.beginPath();
	c.ellipse(50, 50, 45, 45, 0, 0, 2*Math.PI);
	c.closePath();
	c.strokeStyle = "black";
	c.stroke();
	c.fillStyle = "yellow";
	c.fill();
	return c;
}

function retangulo(c){
	c.beginPath();
	c.rect(0, 0, 100, 100);
	c.closePath();
	c.strokeStyle = "black";
	c.stroke();
	c.fillStyle = "blue";
	c.fill();
	return c;
}

function main(){
	var c2d = document.getElementById("exercicios").getContext("2d");
	retangulo(c2d);
	circulo(c2d);
}
*/

/* Exercicio 9
function path1(){
	var p = new Path2D();
	p.moveTo(80, 0);
	p.quadraticCurveTo(0, 50, 80, 100);	
	// p.moveTo(80, 0);
	p.quadraticCurveTo(40, 50, 80, 0);
	return p;
}

function path2(){
	var p = new Path2D();
	p.moveTo(80, 0);
	p.quadraticCurveTo(40, 50, 80, 100);
	return p;
}

function lua(c){
	var p_path1 = path1();
	// var p_path2 = path2();

	c.fillStyle = "steelblue";
	c.fill(p_path1);
	//c.fill(p_path2);
}

function main(){
	var c2d = document.getElementById("exercicios").getContext("2d");
	lua(c2d);
}
*/

/* Exercicio 11
function rotacao(c){
	var x      = 78;
	var y      = 78;
	var width  = 100;
	var height = 100;
	var cx     = x + 0.5 * width;   
	var cy     = y + 0.5 * height;  
	
	c.translate(cx, cy);              
	c.rotate( (Math.PI / 180) * 45); 
	c.translate(-cx, -cy);           

	c.strokeStyle = "black";
	c.fillStyle = "red";
	c.lineWidth = 1.0;
	c.rect(x, y, width, height);
	c.stroke();
	c.fill();
}

function main(){
	var c2d = document.getElementById("exercicios").getContext("2d");
	rotacao(c2d);
}
*/

/* Exercicio 12
function tabuleiro(c){
	c.lineWidth = 1.0;
	c.strokeStyle = "black";
	c.fillStyle = "black";

	for(let i = 0; i <= 8; i++){
		for(let j = 0; j <= 8; j++){
			// traçar as linhas horizontais do tabuleiro
			c.moveTo(0, 32 * j);
			c.lineTo(256, 32 * j);
			c.stroke();
            // traçar as linhas verticais do tabuleiro
			c.moveTo(32 * i, 0);
			c.lineTo(32 * i, 256);
			c.stroke();

			let x_csesq;
			for(let linha = 0; linha < 8; linha++){
				for(let coluna = 0; coluna < 8; coluna+=2){
					if(linha % 2 === 0){
						x_csesq = (coluna + 1) * 32;
						c.fillRect(x_csesq, (linha * 32), 32, 32);
					}
					if(linha % 2 !== 0){
						x_csesq = coluna * 32;
						c.fillRect(x_csesq, (linha * 32), 32, 32);

					}	
				}
			}
		}
	}
}

function main(){
	var c2d = document.getElementById("exercicios").getContext("2d");
	tabuleiro(c2d);
}
*/

// Exercicio 14
function set_defaultStyle(){
	this.lineWidth = 1.0;
	this.strokeStyle = "black";
}

function big_circle(){
	this.beginPath();
	this.ellipse(128, 128, 128, 128, 0, 0, 2*Math.PI);
	this.closePath();
	this.stroke();
}

function small_circle_up(){
	this.beginPath();
	this.ellipse(128, 60, 30, 30, 0, 0, 2*Math.PI);
	this.closePath();
	this.stroke();
	this.fillStyle = "white";
	this.fill();
} 

function small_circle_down(){
	this.beginPath();
	this.ellipse(128, 196, 30, 30, 0, 0, 2*Math.PI);
	this.closePath();
	this.stroke();
	this.fillStyle = "black";
	this.fill();
}

function curve(){
	this.beginPath();
	this.arc(128, 128, 128, Math.PI / 2, (3 / 2)*Math.PI);
	this.moveTo(128, 256);
	this.quadraticCurveTo(30, 190, 128, 128);
	this.quadraticCurveTo(226, 66, 128, 0);
	this.stroke();
	this.fill("evenodd");
}

function main(){
	let canvas_element = document.getElementById("exercicios");
    let gc = canvas_element.getContext("2d");

    gc.style = set_defaultStyle;
    gc.big = big_circle;
    gc.up = small_circle_up;
    gc.down = small_circle_down;
    gc.curve = curve;
    
    gc.style();
    gc.big();
    gc.curve();
    gc.up();
    gc.down();
}


/* Exercicio 15 ou 16?
function gradient(c){
	let g = c.createRadialGradient(128, 64, 32, 128, 64, 64);
	g.addColorStop(0.0, "red");
	g.addColorStop(1.0, "black");
	c.fillStyle = g;
	c.fillRect(0, 0, 256, 128);
}

function main(){
	let c2d = document.getElementById("exercicios").getContext("2d");
    gradient(c2d);
}
*/
