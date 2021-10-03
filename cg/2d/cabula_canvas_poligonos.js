function draw_background(c, color) {
    c.fillStyle = color;
    c.fillRect(0, 0, 256, 256);
}

function star(n, r) {
    points = new Array(2 * n);
    let step_angle = Math.PI / n;
    let angle = 0.0;
    for (let i = 0; i < 2 * n; i++) {
        let radius = 1.0;
        if (i % 2 == 0) {
            radius = r;
        }

        points[i] = {
            x: radius * Math.cos(angle),
            y: radius * Math.sin(angle)
        }

        angle = angle + step_angle;
    }
    return points;
}

function draw_points(c, a) {
    let n = a.length;

    c.beginPath();
    c.moveTo(a[0].x, a[0].y);
    for (let i = 1; i < n; i++) {
        c.lineTo(a[i].x, a[i].y);
    }
    c.lineTo(a[0].x, a[0].y);
    c.closePath();
}

function nagon(n) {
    let points = new Array(n);
    let step_angle = 2.0 * Math.PI / n;
    let angle = 0.0;
    for (let i = 0; i < n; i++) {
        points[i] = {
            x: Math.cos(angle),
            y: Math.sin(angle)
        }
        angle = angle + step_angle;
    }
    return points;
}

function print_points(a) {
    let n = a.length;
    for (let i = 0; i < n; i++) {
        console.log(a[i].x + " " + a[i].y);
    }
}


function main() {
    //
    //  n-ágono regular.
    //
    let nagon_gc = document.getElementById("nagon").getContext("2d");
    draw_background(nagon_gc, "crimson");
    let num_points = 8;
    let nagon_points = nagon(num_points);
    let alpha_0 = Math.PI / num_points;

    nagon_gc.save();
    nagon_gc.translate(128, 128);
    nagon_gc.rotate(-alpha_0);
    nagon_gc.scale(64, 64);
    draw_points(nagon_gc, nagon_points);
    nagon_gc.restore();
    nagon_gc.strokeStyle = "white";
    nagon_gc.lineWidth = 8;
    nagon_gc.stroke();
    nagon_gc.fillStyle = "khaki";
    nagon_gc.fill();
    //
    //  Estrela
    //
    let star_gc = document.getElementById("star").getContext("2d");
    draw_background(star_gc, "steelblue");

    num_points = 7;
    let star_points = star(num_points, 0.2);
    alpha_0 = Math.PI / num_points + Math.PI / 2.0;

    star_gc.save();
    star_gc.translate(128, 128);
    star_gc.rotate(-alpha_0);
    star_gc.scale(64, 64);
    draw_points(star_gc, star_points);
    star_gc.restore();
    star_gc.strokeStyle = "white";
    star_gc.lineWidth = 2;
    star_gc.stroke();
    star_gc.fillStyle = "khaki";
    star_gc.fill();
}