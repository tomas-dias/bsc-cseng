/**
 * 
 * Setup the rendering context and build a model
 *
 **/
function init(mesh) {
    //
    //  Document
    //
    renderer = new THREE.WebGLRenderer({
        alpha: true
    });
    //renderer.setClearColor("khaki")
    let size = Math.min(parent.innerWidth, 512);
    renderer.setSize(size, size);
    let div_container = document.getElementById("container");
    div_container.appendChild(renderer.domElement);
    //
    //  Scene (World, Model)
    //
    scene = new THREE.Scene();
    //
    //  Camera (and TrackballControls)
    //
    camera = new THREE.PerspectiveCamera(
        35, // abertura
        512 / 512, // proporção largura/altura
        0.1, // corte perto
        10000 // corte longe
    );
    camera.position.set(-2.5, 0, 20);
    camera.lookAt(scene.position);
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    //
    //  Lights
    //  
    let ambient_light = new THREE.AmbientLight(0x7F7F7F);
    scene.add(ambient_light);
    //
    let point_light_1 = new THREE.PointLight(0x3F3F3F);
    point_light_1.position.set(5, 0, 0);
    scene.add(point_light_1);
    // //
    let point_light_2 = new THREE.PointLight(0x3F3F3F);
    point_light_2.position.set(-5, 0, 0);
    scene.add(point_light_2);
    //
    //
    //
    scene.add(mesh);
    //
    //  Return camera, scene, etc
    //
    return {
        camera: camera,
        scene: scene,
        renderer: renderer,
        controls: controls
    }
}
/**
 *
 * Animate the model
 *
 */
function animate(step) {
    requestAnimationFrame(function () {
        animate(step);
    });
    step.controls.update();
    step.renderer.render(step.scene, step.camera);
}
/**
 *
 *  Make a model
 *
 */
function model() {
    //
    let keel_g = keel_geometry();
    let keel_m = new THREE.MeshLambertMaterial({
        color: "darkseagreen"
    });
    keel_m.side = THREE.DoubleSide;
    let keel = new THREE.Mesh(keel_g, keel_m);
    keel.scale.set(1, 2, 1);
    //
    let bow_g = bow_geometry();
    let bow_m = new THREE.MeshLambertMaterial({
        color: "crimson"
    });
    bow_m.side = THREE.DoubleSide;
    let bow = new THREE.Mesh(bow_g, bow_m);
    bow.rotation.y = -Math.PI / 2;
    bow.position.set(0, 2, 0);
    //
    let stern_g = bow_geometry();
    let stern_m = new THREE.MeshLambertMaterial({
        color: "steelblue"
    });
    stern_m.side = THREE.DoubleSide;
    let stern = new THREE.Mesh(stern_g, stern_m);
    stern.scale.set(1, -1, 1);
    stern.position.set(0, -2, 0);
    stern.rotation.y = -Math.PI / 2;
    //
    let boat = new THREE.Object3D();
    boat.add(keel);
    boat.add(bow);
    boat.add(stern);
    boat.rotation.y = Math.PI / 2;
    boat.scale.set(1, 1.5, 1.75);

    return boat;
}
/**
 *
 *  Model: Keel geometry
 *
 */
function keel_geometry() {
    //
    //  Geometry
    //
    let cross_section_points = [
        new THREE.Vector2(0.9, -1.),
        new THREE.Vector2(0.9, 0.5),
        new THREE.Vector2(0.0, 0.9),
        new THREE.Vector2(-.9, 0.5),
        new THREE.Vector2(-.9, -1.),
        new THREE.Vector2(-1., -1.),
        new THREE.Vector2(-1., 0.5),
        new THREE.Vector2(0.0, 1.0),
        new THREE.Vector2(1.0, 0.5),
        new THREE.Vector2(1.0, -1.),
        new THREE.Vector2(0.9, -1.)
    ];
    let cross_section = new THREE.Shape(cross_section_points);
    //
    //  Spine
    //
    let spine_points = [
        new THREE.Vector3(0, -1, 0),
        new THREE.Vector3(0, 1, 0)
    ];
    //
    let spine = new THREE.CatmullRomCurve3(spine_points);
    //
    let parameters = {
        extrudePath: spine // <-- the spine goes here
    };
    //
    //  Geometry
    //
    return new THREE.ExtrudeBufferGeometry(cross_section, parameters);
}
/**
 *
 *  Model: Bow geometry
 *
 */
function bow_geometry() {
    //
    //  Coordinates
    //
    let coordinates = [
        new THREE.Vector3(0.9, 0.0, -1.),
        new THREE.Vector3(0.9, 0.0, 0.5),
        new THREE.Vector3(0.0, 0.0, 0.9),
        new THREE.Vector3(-.9, 0.0, 0.5),
        new THREE.Vector3(-.9, 0.0, -1.),
        new THREE.Vector3(-1., 0.0, -1.),
        new THREE.Vector3(-1., 0.0, 0.5),
        new THREE.Vector3(0.0, 0.0, 1.0),
        new THREE.Vector3(1.0, 0.0, 0.5),
        new THREE.Vector3(1.0, 0.0, -1.),
        new THREE.Vector3(0.0, 1.9, -1.),
        new THREE.Vector3(0.0, 1.9, -.5),
        new THREE.Vector3(0.0, 2.0, -1.),
        new THREE.Vector3(0.0, 2.0, -.5)
    ];
    //
    //  Faces (triangles!)
    //
    let faces = [
        //
        new THREE.Face3(0, 1, 8),
        new THREE.Face3(0, 8, 9),
        new THREE.Face3(1, 2, 7),
        new THREE.Face3(1, 7, 8),
        new THREE.Face3(2, 3, 7),
        new THREE.Face3(3, 6, 7),
        new THREE.Face3(3, 4, 6),
        new THREE.Face3(4, 5, 6),
        //
        new THREE.Face3(0, 10, 11),
        new THREE.Face3(0, 11, 1),
        new THREE.Face3(4, 3, 11),
        new THREE.Face3(4, 11, 10),
        new THREE.Face3(3, 2, 11),
        new THREE.Face3(2, 1, 11),
        //
        new THREE.Face3(5, 13, 6),
        new THREE.Face3(5, 12, 13),
        new THREE.Face3(7, 6, 13),
        new THREE.Face3(8, 7, 13),
        new THREE.Face3(9, 8, 13),
        new THREE.Face3(9, 13, 12),
        //
        new THREE.Face3(0, 9, 10),
        new THREE.Face3(9, 12, 10),
        new THREE.Face3(5, 4, 10),
        new THREE.Face3(5, 10, 12)
    ];
    //
    //  Geometry
    //
    let bow = new THREE.Geometry();
    bow.vertices = coordinates;
    bow.faces = faces;
    bow.computeFaceNormals(); // get the right "outside"
    bow.computeBoundingSphere(); // to help the rendering system
    return bow;
}
/**
 *
 *  Entry function
 *
 */
function main() {
    let step = init(model());
    animate(step);
}