function arrow() {
    this.beginPath();
        this.moveTo(0, 0);
        this.lineTo(1, 0);
        this.moveTo(1, 0);
        this.lineTo(0.95, -0.05);
        this.lineTo(0.95, 0.05);
        this.lineTo(1, 0);
    this.closePath();
}

function axes() {
    // Save line width
    let save_lineWidth = this.lineWidth;
    this.lineWidth = 0.02;
    // X Axis
    this.arrow()
    // Aspect
    this.strokeStyle = "crimson";
    this.stroke();
    this.fillStyle = "crimson";
    this.fill();
    //
    // Y Axis
    //
    this.enter(0, 0, 1, 1, 1.5707963268);
    this.arrow();
    this.leave();
    // Aspect
    this.strokeStyle = "seagreen";
    this.stroke();
    this.fillStyle = "seagreen";
    this.fill();
    // Restore line width
    this.lineWidth = save_lineWidth;
}

function some_shape() {
    this.lineWidth = 0.01;
    this.enter(0, 0, 1, 1, 0.7854);
    this.arrow();
    this.leave();
    // Aspect
    this.strokeStyle = "steelblue";
    this.stroke();
    this.fillStyle = "steelblue";
    this.fill();
}

function enter(dx, dy, sx, sy, a) {
    this.save();
    this.rotate(a);
    this.translate(dx, dy);
    this.scale(sx, sy);
}

function leave() {
    this.restore();
}

function grid() {
    this.lineWidth = 0.005;
    this.beginPath();
    for (let i = 1; i <= 10; i++) {
        this.moveTo(0, i * 0.1);
        this.lineTo(1, i * 0.1);

        this.moveTo(i * 0.1, 0);
        this.lineTo(i * 0.1, 1);
    }
    this.moveTo(0, 0);
    this.closePath();
}

function main() {
    let canvas_element = document
        .getElementById("canvas");
    let gc = canvas_element.getContext("2d");
    gc.enter = enter;
    gc.leave = leave;
    gc.block = some_shape;
    gc.axes = axes;
    gc.arrow = arrow;
    gc.grid = grid;
    

    gc.enter(16, 16, 128, 128, 0.0);
        gc.axes();
        gc.grid();
        // Aspect
        gc.strokeStyle = "gray";
        gc.stroke();
        gc.fillStyle = "gray";
        gc.fill();

        gc.enter(0.2, 0.2, 1, 1, 0);
        gc.block(); 
        gc.leave();
    gc.leave();
}