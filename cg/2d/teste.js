function enter(dx, dy, sx, sy, a)
{
    this.save();
    this.rotate(a);
    this.translate(dx, dy);
    this.scale(sx, sy);
}


function leave()
{
    this.restore();
}


function contorno_cabeca()
{
    this.beginPath();
        this.ellipse(50, 50, 30, 30, 0, 0, 2 * Math.PI);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function cabelo()
{
    this.beginPath();
        this.moveTo(35, 20);
        this.bezierCurveTo(40, 10, 50, 17, 55, 15);
        this.lineTo(52, 18);
        this.quadraticCurveTo(55, 19, 60, 15);
        this.bezierCurveTo(50, 33, 43, 19, 35, 20);
    this.closePath();
    this.strokeStyle = "grey";
    this.stroke();
    this.fillStyle = "black";
    this.fill();
}


function olho()
{
    this.beginPath();
        this.ellipse(35, 50, 3.5, 3.5, 0, 0, 2 * Math.PI);
    this.closePath();
    this.strokeStyle = "grey";
    this.stroke();
    this.fillStyle = "black";
    this.fill();
}


function sobrancelha()
{
    this.beginPath();
        this.moveTo(40, 44.5);
        this.quadraticCurveTo(36, 40, 30, 43);
        this.quadraticCurveTo(36, 42, 40, 44.5);
    this.closePath();
    this.strokeStyle = "grey";
    this.stroke();
    this.fillStyle = "black";
    this.fill();
}


function boca()
{
    this.beginPath();
        this.moveTo(40, 65);
        this.quadraticCurveTo(50, 68, 61, 65);
        this.quadraticCurveTo(50, 76, 40, 65);
    this.closePath();
    this.strokeStyle = "grey";
    this.stroke();
    this.fillStyle = "black";
    this.fill();
}


function orelha()
{
    this.beginPath();
        this.moveTo(80, 50);
        this.quadraticCurveTo(80, 46.5, 85, 43);
        this.quadraticCurveTo(87, 53.5, 80, 57);
        this.lineTo(80, 50);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function brinco()
{
    this.beginPath();
        this.moveTo(86, 50);
        this.quadraticCurveTo(88, 57, 84, 58);
        this.quadraticCurveTo(80, 57, 82, 50);
        this.lineTo(83, 50);
        this.quadraticCurveTo(82, 56, 84, 56);
        this.quadraticCurveTo(86, 56, 85, 50);
        this.lineTo(86, 50);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#f4d400";
    this.fill();
}


function nariz()
{
    this.beginPath();
        this.moveTo(48, 60);
        this.quadraticCurveTo(50, 60.5, 52, 60);
        this.quadraticCurveTo(53, 60.5, 52, 61);
        this.quadraticCurveTo(50, 62, 48, 61);
        this.quadraticCurveTo(47, 60.5, 48, 60);
    this.closePath();
    this.strokeStyle = "grey";
    this.stroke();
    this.fillStyle = "black";
    this.fill();
}


function cabeca()
{
    this.contorno_cabeca();
    this.enter(-73, -35, 3, 3, 0);
        this.cabelo();
    this.leave();
    this.orelha();
    this.enter(100, 0, -1, 1, 0);
        this.orelha();
    this.leave();
    this.enter(0, 2, 1, 1, 0);
        this.brinco();
    this.leave();
    this.enter(100, 2, -1, 1, 0);
        this.brinco();
    this.leave();
    this.sobrancelha();
    this.enter(100, 0, -1, 1, 0);
        this.sobrancelha();
    this.leave();
    this.olho();
    this.enter(30, 0, 1, 1, 0);
        this.olho();
    this.leave();
    this.enter(-12.5, -15, 1.25, 1.25, 0);
        this.nariz();
    this.leave();
    this.boca();
}


function pescoco()
{
    this.beginPath();
        this.moveTo(26, 89);
        this.quadraticCurveTo(30, 90, 42, 78);
        this.lineTo(62, 78);
        this.quadraticCurveTo(70 ,90, 84, 91);
        this.lineTo(80, 105);
        this.lineTo(26, 89);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function braco_esquerdo()
{
    this.beginPath();
        this.moveTo(13, 93);
        this.quadraticCurveTo(7, 105, 8, 108);
        this.quadraticCurveTo(23, 115, 53, 100);
        this.lineTo(34, 93);
        this.lineTo(14, 92);
        this.lineTo(13, 93);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function mao_esquerda()
{
    this.beginPath();
        this.moveTo(43, 87);
        this.lineTo(82, 80);
        this.quadraticCurveTo(100, 89, 98, 93);
        this.lineTo(91, 98);
        this.lineTo(61, 90);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function mao_braco_direitos()
{
    this.beginPath();
        this.moveTo(25, 85);
        this.lineTo(10, 92);
        this.quadraticCurveTo(9, 93, 10, 94);
        this.lineTo(21, 92);
        this.quadraticCurveTo(26, 96, 38, 94);
        this.lineTo(60, 102);
        this.quadraticCurveTo(100, 113, 98, 93);
        this.quadraticCurveTo(95, 97, 82, 92);
        this.lineTo(25, 85);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
}


function maos_bracos()
{
    this.enter(0, 10, 1, 1, 0);
        this.enter(0, -7, 1, 1, 0);
            this.braco_esquerdo();
        this.leave();
        this.mao_esquerda();
        this.enter(7, -8, 1, 1, (5 * Math.PI) / 180);
            this.mao_braco_direitos();
        this.leave();
    this.leave();
}


function colete_1()
{
    this.beginPath();
        this.moveTo(25, 90);
        this.lineTo(35, 80);
        this.quadraticCurveTo(70, 120, 8, 135);
        this.lineTo(25, 90);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#fe8400";
    this.fill();
}


function colete_2()
{
    this.beginPath();
        this.moveTo(82, 90);
        this.lineTo(67, 80);
        this.quadraticCurveTo(40, 110, 70, 137);
        this.quadraticCurveTo(100, 105, 82, 90);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#fe8400";
    this.fill();
}


function contorno_tronco_cauda()
{
    this.beginPath();
        this.moveTo(23, 100);
        this.quadraticCurveTo(2, 150, 35, 179);
        this.quadraticCurveTo(70, 200, 100, 170);
        this.bezierCurveTo(100, 170, 150, 100, 205, 130);
        this.bezierCurveTo(175, 45, 90, 175, 55, 160);
        this.bezierCurveTo(40, 150, 90, 115, 80, 110);
        this.lineTo(23, 100);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#00c6f2";
    this.fill();
    this.colete_1();
    this.colete_2();
}

function genio()
{
    this.pescoco();
    this.cabeca();
    this.contorno_tronco_cauda();
    this.maos_bracos();
}


function lamparina_1()
{
    this.beginPath();
        this.moveTo(205, 145);
        this.lineTo(203, 149);
        this.lineTo(205, 154);
        this.lineTo(200, 160);
        this.quadraticCurveTo(190, 174, 182, 168);
        this.lineTo(168, 160);
        this.lineTo(165, 160);
        this.quadraticCurveTo(200, 200, 222, 170);
        this.quadraticCurveTo(210, 168, 210, 165);
        this.lineTo(205, 154);
        this.lineTo(207, 149);
        this.lineTo(205, 145);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#fe8400";
    this.fill();
}


function lamparina_2()
{
    this.beginPath();
        this.moveTo(190, 210);
        this.quadraticCurveTo(200, 200, 210, 210);
        this.lineTo(190, 210);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#fe8400";
    this.fill();
}


function lamparina_3()
{
    this.beginPath();
        this.moveTo(223, 171);
        this.quadraticCurveTo(224, 171.5, 234, 171);
        this.quadraticCurveTo(224, 182, 216, 180);
        this.lineTo(216, 178);
        this.quadraticCurveTo(222, 180, 228, 173);
        this.lineTo(222, 173);
        this.lineTo(223, 171);
    this.closePath();
    this.strokeStyle = "black"; 
    this.stroke();
    this.fillStyle = "#fe8400";
    this.fill();
}


function lamparina()
{
    this.enter(8, -12.8, 1, 1, (3 * Math.PI) / 180);
        this.lamparina_3();
    this.leave();
    this.lamparina_1();
    this.enter(0, -22, 1, 1, 0);
        this.lamparina_2();
    this.leave();
}


function init_model()
{
    let model = {
        leader: {
            x: 0.2,
            y: 0.2,
        },
        background: {
            color: "khaki",
        },
        follower: {
            x: 0.8,
            y: 0.2,
            color: "steelblue",
            speed: 0.008,
        },
        last_timestamp: performance.now(),
        frame_count: 0,
    }
        
    return model;
}


function render(model)
{
    this.enter(0, 0, this.canvas.width, this.canvas.height, 0);
    //
    //this.message(`FRAME COUNT: ${model.frame_count}`);
    //
    this.fillStyle = model.background.color;
    this.fillRect(0, 0, 1, 1);
    //
    /*
    this.fillStyle = model.follower.color;
    this.fillRect(
    model.follower.x - 0.05, model.follower.y - 0.05,
    0.05, 0.05);
    */
    //
    this.leave();

    this.enter(model.leader.x * 400, model.leader.y * 400, 0.5, 0.5, 0);
    this.genio();
    this.leave();

}


function update(model)
{
    //let timestamp = performance.now();
    //let dt = timestamp - model.last_timestamp;
    //model.last_timestamp = timestamp;

    TWEEN.update();
    
    let dx = model.follower.speed * (model.leader.x - model.follower.x);
    let dy = model.follower.speed * (model.leader.y - model.follower.y);
    //
    model.follower.x += dx;
    model.follower.y += dy;

    return model;
}


function animate(gc)
{
    let model = init_model(gc);

    let down = new TWEEN.Tween(model.leader).to({y: 0.8}, 1500).easing(TWEEN.Easing.Bounce.In);
    let right = new TWEEN.Tween(model.leader).to({x: 0.8}, 1500).easing(TWEEN.Easing.Elastic.InOut);
    let up = new TWEEN.Tween(model.leader).to({y: 0.2}, 1500).easing(TWEEN.Easing.Elastic.InOut);
    let left = new TWEEN.Tween(model.leader).to({x: 0.2}, 1500).easing(TWEEN.Easing.Elastic.InOut);

    function animation_step()
    {
        model = update(model);
        gc.render(model);

        requestAnimationFrame(animation_step);
    };

    down.chain(right);
    right.chain(up);
    up.chain(left);
    left.chain(down);

    down.start(); // START THE TWEEN
    requestAnimationFrame(animation_step); // START THE LOOP
}

function main() {
    console.log("I'm alive!");
    //
    // SETUP GRAPHICS CONTEXT
    //
    let gc = document.getElementById("canvas").getContext("2d");
    gc.render = render;
    gc.enter = enter;
    gc.leave = leave;

    // Cabeça & Pescoço
    
    gc.contorno_cabeca = contorno_cabeca;
    gc.cabelo = cabelo;
    gc.orelha = orelha;
    gc.brinco = brinco;
    gc.sobrancelha = sobrancelha;
    gc.olho = olho;
    gc.nariz = nariz;
    gc.boca = boca;
    gc.cabeca = cabeca;
    gc.pescoco = pescoco;
     
    // Braços, Tronco & Cauda
     
    gc.mao_braco_direitos = mao_braco_direitos;
    gc.braco_esquerdo = braco_esquerdo;
    gc.mao_esquerda = mao_esquerda;
    gc.maos_bracos = maos_bracos;
    gc.contorno_tronco_cauda = contorno_tronco_cauda;
    gc.colete_1 = colete_1;
    gc.colete_2 = colete_2;
     
    // Outros
     
    gc.lamparina_1 = lamparina_1;
    gc.lamparina_2 = lamparina_2;
    gc.lamparina_3 = lamparina_3;
    gc.lamparina = lamparina;
     
    //
     
    gc.genio = genio;
  
    //let message = document.getElementById("message")
    //gc.message = (text) => { message.innerHTML = text; }
    let width = 500;
    let height = 500;
    gc.canvas.width = width;
    gc.canvas.height = height;
    //
    // ANIMATE
    //
    //init_model(gc);
    animate(gc);
}
    