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


function init_model()
{
    let model = {
        background: {
            color: "khaki",
        },
        leader: {
            x: 0.2,
            y: 0.2,
            color: "crimson",
        },
        follower: {
            x: 0.8,
            y: 0.2,
            speed: 0.005,
            color: "steelblue",
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
    this.fillStyle = model.leader.color;
    this.fillRect(
    model.leader.x - 0.05, model.leader.y - 0.05,
    0.1, 0.1);
    //
    this.fillStyle = model.follower.color;
    this.fillRect(
    model.follower.x - 0.05, model.follower.y - 0.05,
    0.1, 0.1);
    //
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
    let model = init_model();

    let down = new TWEEN.Tween(model.leader).to({y: 0.8}, 1500).easing(TWEEN.Easing.Elastic.InOut);
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
    //let message = document.getElementById("message")
    //gc.message = (text) => { message.innerHTML = text; }
    let width = 256;
    let height = 256;
    gc.canvas.width = width;
    gc.canvas.height = height;
    //
    // ANIMATE
    //
    animate(gc);
}
    