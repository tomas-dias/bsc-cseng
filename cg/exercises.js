// Exercicio 1 do Exercícios Javascript - Conjunto 02
function argmax(y){
    let u = -Infinity;
    let ui;
    let z;

    if(y.length === 0){
        ui = -1;
        z = {
            index: ui,
            value: u
        }
        return z;
    }

    for(let i = 0; i < y.length; i++){
        if(u < y[i]){
            u = y[i];
            ui = i;
        }
    }

    z = {
        index: ui,
        value: u
    }
    return z;
}

// Exercicio 2 do Exercícios Javascript - Conjunto 02
function r(x){
   return Math.round(x * 1000) / 1000;
}

function grafico(func, x_min, x_max, num_points){
   let y = [];
   let aux;
   let z;
   if(x_min < x_max){
       aux = (x_max - x_min) / (num_points - 1);
       for(let i = 0; i < num_points; i++){
           z = {
               x: r(x_min + aux*i),
               y: r(func(x_min + aux*i))
           }
           y.push(z);
       }
       return y;
   }
   else {
       let arr = [];
       return arr;
   }   
}

// Exercicio 3 do Exercícios Javascript - Conjunto 02
function r(x){
   return Math.round(x * 1000) / 1000;
}

function derivada(func, epsilon){
   if(epsilon > 0){
       return function(x){
           return r((func(x+epsilon) - func(x-epsilon)) / (2 * epsilon));
       }
   }
}

// Exercicio 4 do Exercícios Javascript - Conjunto 02
function dot({x: ax, y: ay, z: az}, {x: bx, y: by, z: bz}){
   return ax*bx + ay*by + az*bz;
}

function norm({x: ax, y: ay, z: az}){
   let produtoi = dot({x: ax, y: ay, z: az}, {x: ax, y: ay, z: az});
   return Math.sqrt(produtoi);
}

function vers({x: ax, y: ay, z: az}){
   let norma = norm({x: ax, y: ay, z: az});
   return {x: ax / norma, y: ay / norma, z: az / norma};
}

function angle({x: ax, y: ay, z: az}, {x: bx, y: by, z: bz}){
   let produtoi = dot({x: ax, y: ay, z: az}, {x: bx, y: by, z: bz});
   let norma_a = norm({x: ax, y: ay, z: az});
   let norma_b = norm({x: bx, y: by, z: bz});
   return Math.acos(produtoi / (norma_a * norma_b));
}

function orth({x: ax, y: ay, z: az}, {x: bx, y: by, z: bz}){
   return {x: ay*bz - az*by, y: ax*bz - az*bx, z: ax*by - ay*bx};
}

// Exercicio 6 da Antevisão Parcial do MT02 (sem avaliação) - Com Exercícios Novos
function list_lens(x){
   let str;
   let n;
   let arr = [];
   for(let i = 0; i < x.length; i++){
       str = x[i];
       n = str.length;
       arr.push(n);
   }
   return arr;
}

// Alternativa
function list_lens(x){
   let result = new Array(x.length);
   for (let i in x) result[i] = x[i].length;
   return result;
}

// Possivel resolução do exercicio de js do conjunto 04 do mini-teste 02
function count_pred(p, x){
   let count = 0;
   for(let xi of x){
       if(p(xi) === true){
           count = count + 1;
       }
   }
   return count;
}

// Exercicio 2 da Antevisão Parcial do MT03
function ball_step(model){
    
    model.ball.x = model.ball.x + model.ball.vx;
    model.ball.y = model.ball.y + model.ball.vy;
    
    if(model.ball.x - model.ball.r < 0){
        model.ball.vx = -model.ball.vx;
        model.ball.x = model.ball.r;
    }
    if(model.ball.x + model.ball.r > model.width){
        model.ball.vx = -model.ball.vx;
        model.ball.x = model.width - model.ball.r;
    }
    if(model.ball.y - model.ball.r < 0){
        model.ball.vy = -model.ball.vy;
        model.ball.y = model.ball.r;
    }
    if(model.ball.y + model.ball.r > model.height){
        model.ball.vy = -model.ball.vy;
        model.ball.y = model.height - model.ball.r;
    }
    return model;
}

// Alternativa
function ball_step(model){
    //
    let over_x = model.ball.x + model.ball.vx + model.ball.r >= model.width;
    let under_x = model.ball.x + model.ball.vx - model.ball.r <= 0;
    let over_y = model.ball.y + model.ball.vy + model.ball.r >= model.height;
    let under_y = model.ball.y + model.ball.vy - model.ball.r <= 0;
    if (over_x || under_x) { model.ball.vx *= -1; }
    if (over_y || under_y) { model.ball.vy *= -1; }
    //
    return model;
}

// Exercicio 1 do Mini-teste 03 - Conjunto 02
function ball_step(model){
    
    if(model.ball.x - model.square.x > model.alert_distance || model.ball.y - model.square.y > model.alert_distance){
        model.ball.color = "seagreen";
    }
    if(model.ball.x - model.square.x < model.alert_distance || model.ball.y - model.square.y < model.alert_distance){
        model.ball.color = "crimson";
    }
     if(model.ball.x - model.square.x === model.alert_distance || model.ball.y - model.square.y === model.alert_distance){
        model.ball.color = "seagreen";
    }

    return model;
}

// Alternativa
function ball_step(model) {
    //
    let distance = Math.sqrt(
        Math.pow(model.ball.x - model.square.x, 2) + 
        Math.pow(model.ball.y - model.square.y, 2) );
    //
    if(distance < model.alert_distance) {
        model.ball.color = "crimson";
    }
    else{
        model.ball.color = "seagreen";
    }
    //
    return model;
}

// Exercicio 1 do Mini-teste 03 - Conjunto 04
function moon_step(model){
    //
    model.moon.x = model.earth.x + model.d * Math.cos(model.moon.a);
    model.moon.y = model.earth.y - model.d * Math.sin(model.moon.a);
    //
    return model;
}