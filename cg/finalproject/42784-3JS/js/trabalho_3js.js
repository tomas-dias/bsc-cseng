/**
 * Animate the model
 */
function animate(step)
{
    requestAnimationFrame(function () {
        animate(step);
    });
    step.controls.update();
    step.renderer.render(step.scene, step.camera);
    TWEEN.update();
}


function setup_tweens(mesh)
{
    let target_p = {x: -11, y: -1.5};
    let position_p = {x: mesh.p.position.x, y: mesh.p.position.y};
    let tween_p = new TWEEN.Tween(position_p).to(target_p, 3000);

    tween_p.onUpdate(function(){
        mesh.p.position.x = position_p.x;
        mesh.p.position.y = position_p.y;
        console.log(mesh.p.position.x);
        console.log(mesh.p.position.y);
    });

    tween_p.easing(TWEEN.Easing.Elastic.InOut);
    tween_p.start();
    
    //

    let target_a1 = {x: -8, y: -2};
    let position_a1 = {x: mesh.a1.position.x, y: mesh.a1.position.y};
    let tween_a1 = new TWEEN.Tween(position_a1).to(target_a1, 4000);

    tween_a1.onUpdate(function(){
        mesh.a1.position.x = position_a1.x;
        mesh.a1.position.y = position_a1.y;
        console.log(mesh.a1.position.x);
        console.log(mesh.a1.position.y);
    });

    tween_a1.easing(TWEEN.Easing.Elastic.InOut);
    tween_a1.start();

    //

    let target_t1 = {x: -4.75, y: -1.5};
    let position_t1 = {x: mesh.t1.position.x, y: mesh.t1.position.y};
    let tween_t1 = new TWEEN.Tween(position_t1).to(target_t1, 5000);

    tween_t1.onUpdate(function(){
        mesh.t1.position.x = position_t1.x;
        mesh.t1.position.y = position_t1.y;
        console.log(mesh.t1.position.x);
        console.log(mesh.t1.position.y);
    });

    tween_t1.easing(TWEEN.Easing.Elastic.InOut);
    tween_t1.start();

    //

    let target_a2 = {x: -3, y: -2};
    let position_a2 = {x: mesh.a2.position.x, y: mesh.a2.position.y};
    let tween_a2 = new TWEEN.Tween(position_a2).to(target_a2, 6000);

    tween_a2.onUpdate(function(){
        mesh.a2.position.x = position_a2.x;
        mesh.a2.position.y = position_a2.y;
        console.log(mesh.a2.position.x);
        console.log(mesh.a2.position.y);
    });

    tween_a2.easing(TWEEN.Easing.Elastic.InOut);
    tween_a2.start();

    //

    let target_r = {x: 0, y: -1.5};
    let position_r = {x: mesh.r.position.x, y: mesh.r.position.y};
    let tween_r = new TWEEN.Tween(position_r).to(target_r, 7000);

    tween_r.onUpdate(function(){
        mesh.r.position.x = position_r.x;
        mesh.r.position.y = position_r.y;
        console.log(mesh.r.position.x);
        console.log(mesh.r.position.y);
    });

    tween_r.easing(TWEEN.Easing.Elastic.InOut);
    tween_r.start();

    //

    let target_a3 = {x: 2.5, y: -2};
    let position_a3 = {x: mesh.a3.position.x, y: mesh.a3.position.y};
    let tween_a3 = new TWEEN.Tween(position_a3).to(target_a3, 8000);

    tween_a3.onUpdate(function(){
        mesh.a3.position.x = position_a3.x;
        mesh.a3.position.y = position_a3.y;
        console.log(mesh.a3.position.x);
        console.log(mesh.a3.position.y);
    });

    tween_a3.easing(TWEEN.Easing.Elastic.InOut);
    tween_a3.start();

    //

    let target_t2 = {x: 5.75, y: -1.5};
    let position_t2 = {x: mesh.t2.position.x, y: mesh.t2.position.y};
    let tween_t2 = new TWEEN.Tween(position_t2).to(target_t2, 9000);

    tween_t2.onUpdate(function(){
        mesh.t2.position.x = position_t2.x;
        mesh.t2.position.y = position_t2.y;
        console.log(mesh.t2.position.x);
        console.log(mesh.t2.position.y);
    });

    tween_t2.easing(TWEEN.Easing.Elastic.InOut);
    tween_t2.start();

    //

    let target_e = {x: 8.5, y: -1.5};
    let position_e = {x: mesh.e.position.x, y: mesh.e.position.y};
    let tween_e = new TWEEN.Tween(position_e).to(target_e, 10000);

    tween_e.onUpdate(function(){
        mesh.e.position.x = position_e.x;
        mesh.e.position.y = position_e.y;
        console.log(mesh.e.position.x);
        console.log(mesh.e.position.y);
    });

    tween_e.easing(TWEEN.Easing.Elastic.InOut);
    tween_e.start();
}


/**
 * Setup the rendering context and build a model
 **/
function init(mesh)
{
    let renderer = new THREE.WebGLRenderer({
        alpha: true
    });
    let size = Math.min(parent.innerWidth, 512);
    renderer.setSize(size, size);
    renderer.setClearColor(0x87CEEB);
    let div_container = document.getElementById("container");
    div_container.appendChild(renderer.domElement);
    //
    // Scene
    //
    let scene = new THREE.Scene();
    //
    // Camera (and TrackballControls)
    //
    let camera = new THREE.PerspectiveCamera(
        45, // abertura
        512 / 512, // proporção largura/altura
        1, // corte perto
        10000 // corte longe
    );
    camera.position.set(-1, 0, 20);
    camera.lookAt(scene.position);
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    //
    // Lights
    //  
    let ambient_light = new THREE.AmbientLight(0xFFFFFF);
    scene.add(ambient_light);
    //
    // Setup Tweens
    //
    setup_tweens(mesh);
    //
    //
    //
    scene.add(mesh);
    //
    // Return camera, scene, etc
    //
    return {
        camera: camera,
        scene: scene,
        renderer: renderer,
        controls: controls
    }
}


function model()
{
    let p = letra_p();
    p.rotation.x = Math.PI / 2;
    p.rotation.y = 3 * Math.PI / 2;

    //

    let a1 = letra_a();
    a1.rotation.x = Math.PI / 2;
    a1.rotation.y = 3 * Math.PI / 2;

    //

    let t1 = letra_t();
    t1.scale.set(1.25, 1, 1);
    t1.rotation.x = Math.PI / 2;
    t1.rotation.y = 3 * Math.PI / 2;

    //

    let a2 = letra_a();
    a2.rotation.x = Math.PI / 2;
    a2.rotation.y = 3 * Math.PI / 2;

    //

    let r = letra_r();
    r.scale.set(1.2, 1, 1);
    r.rotation.x = Math.PI / 2;
    r.rotation.y = 3 * Math.PI / 2;

    //

    let a3 = letra_a();
    a3.rotation.x = Math.PI / 2;
    a3.rotation.y = 3 * Math.PI / 2;

    //

    let t2 = letra_t();
    t2.scale.set(1.25, 1, 1);
    t2.rotation.x = Math.PI / 2;
    t2.rotation.y = 3 * Math.PI / 2;

    //

    let e = letra_e();
    e.scale.set(0.8, 1, 0.6);
    e.rotation.x = Math.PI / 2;
    e.rotation.y = 3 * Math.PI / 2;

    //

    let patarate = new THREE.Object3D();
    patarate.p = p;
    patarate.add(p);
    patarate.p.position.x = -15;
    patarate.p.position.y = 15;
    patarate.a1 = a1;
    patarate.add(a1);
    patarate.a1.position.x = -8;
    patarate.a1.position.y = -20;
    patarate.t1 = t1;
    patarate.add(t1);
    patarate.t1.position.x = -4.75;
    patarate.t1.position.y = 15;
    patarate.a2 = a2;
    patarate.add(a2);
    patarate.a2.position.x = -3;
    patarate.a2.position.y = -20;
    patarate.r = r;
    patarate.add(r);
    patarate.r.position.x = 0;
    patarate.r.position.y = 15;
    patarate.a3 = a3;
    patarate.add(a3);
    patarate.a3.position.x = 2.5;
    patarate.a3.position.y = -20;
    patarate.t2 = t2;
    patarate.add(t2);
    patarate.t2.position.x = 5.75;
    patarate.t2.position.y = 15;
    patarate.e = e;
    patarate.add(e);
    patarate.e.position.x = 15;
    patarate.e.position.y = -20;
    patarate.scale.set(0.65, 0.65, 0.65);
    patarate.position.x = 0.4;

    return patarate;
}


function letra_p()
{
    let cross_section_points = [
        new THREE.Vector2(0, 0),
        new THREE.Vector2(0.25, 0.1),
        new THREE.Vector2(0.5, 0.15),
        new THREE.Vector2(0.75, 0.1),
        new THREE.Vector2(1, 0),
        new THREE.Vector2(0.75, 0.4),
        new THREE.Vector2(0.75, 2),
        new THREE.Vector2(1, 2.05),
        new THREE.Vector2(1.25, 2.1),
        new THREE.Vector2(1.5, 2.2),
        new THREE.Vector2(1.75, 2.3),
        new THREE.Vector2(2, 2.5),
        new THREE.Vector2(2.25, 2.8),
        new THREE.Vector2(2.4, 3.1),
        new THREE.Vector2(2.3, 3.7),
        new THREE.Vector2(2, 4.2),
        new THREE.Vector2(1.75, 4.35),
        new THREE.Vector2(1.5, 4.48),
        new THREE.Vector2(1.25, 4.58),
        new THREE.Vector2(1, 4.65),
        new THREE.Vector2(0.75, 4.6),
        new THREE.Vector2(0.5, 4.5),
        new THREE.Vector2(0.3, 4.3),
        new THREE.Vector2(0.1, 4.2),
        new THREE.Vector2(0, 4.22),
        new THREE.Vector2(0.25, 4),
        new THREE.Vector2(0.5, 4.15),
        new THREE.Vector2(0.75, 4.3),
        new THREE.Vector2(1, 4.35),
        new THREE.Vector2(1.25, 4.3),
        new THREE.Vector2(1.4, 4.2),
        new THREE.Vector2(1.6, 4.1),
        new THREE.Vector2(1.85, 3.8),
        new THREE.Vector2(1.95, 3.5),
        new THREE.Vector2(1.8, 3),
        new THREE.Vector2(1.65, 2.8),
        new THREE.Vector2(1.4, 2.6),
        new THREE.Vector2(1.2, 2.47),
        new THREE.Vector2(1, 2.37),
        new THREE.Vector2(0.75, 2.37),
        new THREE.Vector2(0.75, 2),
        new THREE.Vector2(0.75, 4.6),
        new THREE.Vector2(0.1, 4.2),
        new THREE.Vector2(0, 4.22),
        new THREE.Vector2(0.25, 4),
        new THREE.Vector2(0.24, 0.44),
        new THREE.Vector2(0, 0)
    ];

    let cross_section = new THREE.Shape(cross_section_points);

    let spine_points = [
        new THREE.Vector3(0, 0, 0),
        new THREE.Vector3(0, 1, 0) 
    ];

    let spine =  new THREE.CatmullRomCurve3(spine_points);

    let parameters = {
        steps         : 2,
        extrudePath   : spine }; // <-- the spine goes here

    let pg = new THREE.ExtrudeGeometry( cross_section, parameters ); 

    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_1.png");
    //let specularMap = loader.load("media/");
    let pm = new THREE.MeshBasicMaterial({
        side: THREE.DoubleSide,
        color: 0xFFFFFF,
        //specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap,
    });

    let p = new THREE.Mesh(pg, pm);

    return p;
}


function letra_a()
{
    let cross_section_points = [
        new THREE.Vector2(-0.2, 3.2),
        new THREE.Vector2(1, 4),
        new THREE.Vector2(2, 3.5),
        new THREE.Vector2(1.8, 3.1),
        new THREE.Vector2(1.8, 1),
        new THREE.Vector2(2, 0.8),
        new THREE.Vector2(1.8, 0.6),
        new THREE.Vector2(1.3, 0.9),
        new THREE.Vector2(1, 0.6),
        new THREE.Vector2(0.7, 0.4),
        new THREE.Vector2(0.4, 0.8),
        new THREE.Vector2(0, 1.5),
        new THREE.Vector2(0.4, 2.2), 
        new THREE.Vector2(1.2, 2.5),
        new THREE.Vector2(1.2, 2.3),
        new THREE.Vector2(0.4, 2),
        new THREE.Vector2(0.3, 1.5),
        new THREE.Vector2(0.4, 1.3), 
        new THREE.Vector2(0.7, 0.9),
        new THREE.Vector2(1, 1.1),
        new THREE.Vector2(1.3, 1.4),
        new THREE.Vector2(1.2, 2.5),
        new THREE.Vector2(1.2, 3.2),
        new THREE.Vector2(0.5, 3.5),
        new THREE.Vector2(0, 3),
        new THREE.Vector2(-0.2, 3.2)
    ];

    let cross_section = new THREE.Shape(cross_section_points);

    let spine_points = [
        new THREE.Vector3(0, 0, 0),
        new THREE.Vector3(0, 1, 0) 
    ];

    let spine =  new THREE.CatmullRomCurve3(spine_points);

    let parameters = {
        steps         : 2,
        extrudePath   : spine }; // <-- the spine goes here

    let ag = new THREE.ExtrudeGeometry( cross_section, parameters ); 

    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_2.png");
    //let specularMap = loader.load("media/");
    let am = new THREE.MeshPhongMaterial({
        side: THREE.DoubleSide,
        color: 0xFFFFFF,
        //specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap
    });

    let a = new THREE.Mesh(ag, am);

    return a;
}


function letra_t()
{
    let cross_section_points = [
        new THREE.Vector2(0.5, 0),
        new THREE.Vector2(0.8, 0.3),
        new THREE.Vector2(0.6, 0.4),
        new THREE.Vector2(0.5, 0.6),
        new THREE.Vector2(0.5, 2.5),
        new THREE.Vector2(1, 2.5),
        new THREE.Vector2(1, 2.9),
        new THREE.Vector2(0.5, 2.9),
        new THREE.Vector2(0.5, 3.6),
        new THREE.Vector2(0.2, 3.3),
        new THREE.Vector2(0.1, 3.1),
        new THREE.Vector2(-0.1, 3),
        new THREE.Vector2(-0.3, 2.9),
        new THREE.Vector2(-0.3, 2.5),
        new THREE.Vector2(0, 2.5),
        new THREE.Vector2(0, 0.6),
        new THREE.Vector2(0.2, 0.2),
        new THREE.Vector2(0.5, 0)
    ];

    let cross_section = new THREE.Shape(cross_section_points);

    let spine_points = [
        new THREE.Vector3(0, 0, 0),
        new THREE.Vector3(0, 1, 0) 
    ];

    let spine =  new THREE.CatmullRomCurve3(spine_points);

    let parameters = {
        steps         : 2,
        extrudePath   : spine }; // <-- the spine goes here

    let tg = new THREE.ExtrudeGeometry( cross_section, parameters ); 

    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_1.png");
    //let specularMap = loader.load("media/");
    let tm = new THREE.MeshBasicMaterial({
        side: THREE.DoubleSide,
        color: 0xFFFFFF,
        //specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap,
    });

    let t = new THREE.Mesh(tg, tm);

    return t;
}


function letra_r()
{
    let cross_section_points = [
        new THREE.Vector2(0.5, 0),
        new THREE.Vector2(0.8, 0.3),
        new THREE.Vector2(0.68, 0.32),
        new THREE.Vector2(0.6, 0.4),
        new THREE.Vector2(0.53, 0.47),
        new THREE.Vector2(0.5, 0.6),
        new THREE.Vector2(0.5, 2.5),
        new THREE.Vector2(0.42, 2.57),
        new THREE.Vector2(0.5, 2.65),
        new THREE.Vector2(0.8, 2.75),
        new THREE.Vector2(1.7, 2.55),
        new THREE.Vector2(2.2, 2.9),
        new THREE.Vector2(1.2, 3.2),
        new THREE.Vector2(0.33, 2.7),
        new THREE.Vector2(0.23, 2.8),
        new THREE.Vector2(0.14, 2.9),
        new THREE.Vector2(0, 3),
        new THREE.Vector2(-0.3, 2.6),
        new THREE.Vector2(0, 2.4),
        new THREE.Vector2(-0.1, 2.55),
        new THREE.Vector2(0, 0.6),
        new THREE.Vector2(0.2, 0.2),
        new THREE.Vector2(0.5, 0)
    ];

    let cross_section = new THREE.Shape(cross_section_points);

    let spine_points = [
        new THREE.Vector3(0, 0, 0),
        new THREE.Vector3(0, 1, 0) 
    ];

    let spine =  new THREE.CatmullRomCurve3(spine_points);

    let parameters = {
        steps         : 2,
        extrudePath   : spine }; // <-- the spine goes here

    let rg = new THREE.ExtrudeGeometry( cross_section, parameters ); 

    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_1.png");
    //let specularMap = loader.load("media/");

    let rm = new THREE.MeshPhongMaterial({
        side: THREE.DoubleSide,
        color: 0xFFFFFF,
        //specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap,
    });

    let r = new THREE.Mesh(rg, rm);

    return r;
}


function letra_e()
{
    let cross_section_points = [
        new THREE.Vector2(0.5, 0),
        new THREE.Vector2(1.5, 0.6),
        new THREE.Vector2(1, 0.45),
        new THREE.Vector2(0.5, 0.4),
        new THREE.Vector2(0, 0.6),
        new THREE.Vector2(-0.5, 0.8),
        new THREE.Vector2(-0.9, 1.5),
        new THREE.Vector2(1.5, 3.8),
        new THREE.Vector2(0.5, 4.2),
        new THREE.Vector2(0, 4.1),
        new THREE.Vector2(0.2, 3.85),
        new THREE.Vector2(1, 3.4),
        new THREE.Vector2(-1, 1.7),
        new THREE.Vector2(-1, 2.4),
        new THREE.Vector2(-0.85, 2.9),
        new THREE.Vector2(-0.5, 3.3),
        new THREE.Vector2(0.2, 3.85),
        new THREE.Vector2(0, 4.1),
        new THREE.Vector2(-0.3, 3.9),
        new THREE.Vector2(-0.6, 3.7),
        new THREE.Vector2(-1, 3.3),
        new THREE.Vector2(-1.3, 2.9),
        new THREE.Vector2(-1.55, 2.3),
        new THREE.Vector2(-1.65, 1.7),
        new THREE.Vector2(-1.6, 1.2),
        new THREE.Vector2(-1.35, 0.7),
        new THREE.Vector2(-1, 0.4),
        new THREE.Vector2(-0.5, 0.2),
        new THREE.Vector2(0, 0.1),
        new THREE.Vector2(0.5, 0)
    ];

    let cross_section = new THREE.Shape(cross_section_points);

    let spine_points = [
        new THREE.Vector3(0, 0, 0),
        new THREE.Vector3(0, 1, 0) 
    ];

    let spine =  new THREE.CatmullRomCurve3(spine_points);

    let parameters = {
        steps         : 2,
        extrudePath   : spine }; // <-- the spine goes here

    let eg = new THREE.ExtrudeGeometry( cross_section, parameters ); 

    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_2.png");
    //let specularMap = loader.load("media/");
    let em = new THREE.MeshPhongMaterial({
        color: 0xFFFFFF,
        //specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap,
    });

    let e = new THREE.Mesh(eg, em);

    return e;
}


/**
 *  Entry function
 */
function main()
{
    let step = init(model());
    animate(step);
}