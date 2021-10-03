function contorno() {
    var p = new Path2D();
    p.ellipse(50,50,45,45,0,0,2*Math.PI);
    return p;
}

function nariz() {
    var p = new Path2D();
    p.moveTo(50,33);
    p.lineTo(40,66);
    p.lineTo(60,66);
    p.lineTo(50,33);
    return p;
}

function boca() {
    var p = new Path2D();
    p.rect(33,70,33,5);
    return p;
}

function olho_esquerdo() {
    var p = new Path2D();
    p.ellipse(33,35,10,10,0,0,2*Math.PI);
    return p;
}

function olho_direito() {
    var p = new Path2D();
    p.ellipse(66,35,10,10,0,0,2*Math.PI);
    return p;
}
/**
 * Desenha um rosto no contexto c
 *
 * @param {context} c - contexto onde vai ser desenhado o rosto
 */
function rosto(c) {
    var p_contorno = contorno();
    var p_nariz = nariz();
    var p_boca = boca();
    var p_olho_esquerdo = olho_esquerdo();
    var p_olho_direito = olho_direito();

    c.strokeStyle = "black"; 

    c.fillStyle = "tan";
    c.fill(p_contorno); c.stroke(p_contorno);
    c.fillStyle = "wheat";
    c.fill(p_nariz); c.stroke(p_nariz);
    c.fillStyle = "lightcoral";
    c.fill(p_boca); c.stroke(p_boca);
    c.fillStyle = "powderblue";
    c.fill(p_olho_esquerdo); c.stroke(p_olho_esquerdo);
    c.fill(p_olho_direito); c.stroke(p_olho_direito);
}

function set_defaultStyle(c) {
    c.strokeStyle = "steelblue";
    c.fillStyle = "khaki";
}

function main() {
    var c2d = document.getElementById("canvas").getContext("2d");
    set_defaultStyle(c2d);
    rosto(c2d);
}