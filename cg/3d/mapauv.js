/**
 *  Make a model of the dice with UV mapping
 */
function dice() {
    //
    let coordinates = [
        new THREE.Vector3(+1, -1, +1),
        new THREE.Vector3(+1, -1, -1),
        new THREE.Vector3(-1, -1, -1),
        new THREE.Vector3(-1, -1, +1),
        new THREE.Vector3(+1, +1, +1),
        new THREE.Vector3(+1, +1, -1),
        new THREE.Vector3(-1, +1, -1),
        new THREE.Vector3(-1, +1, +1)
    ];
    //
    let faces = [
        new THREE.Face3(0, 2, 1), new THREE.Face3(0, 3, 2),
        //
        new THREE.Face3(0, 1, 5), new THREE.Face3(0, 5, 4),
        new THREE.Face3(1, 6, 5), new THREE.Face3(1, 2, 6),
        new THREE.Face3(2, 7, 6), new THREE.Face3(2, 3, 7),
        new THREE.Face3(3, 4, 7), new THREE.Face3(3, 0, 4),
        //    
        new THREE.Face3(4, 5, 6), new THREE.Face3(4, 6, 7)
    ]
    //
    //  Symbolic names of UV coordinates (just to ease mapping below)
    //
    let vA = 0.00 / 3,
        vB = 1.00 / 3,
        vC = 2.00 / 3,
        vD = 3.00 / 3,
        uA = 0.00 / 4,
        uB = 1.00 / 4,
        uC = 2.00 / 4,
        uD = 3.00 / 4,
        uE = 4.00 / 4;

    let uv = [
        [new THREE.Vector2(uB, vA), new THREE.Vector2(uA, vB), new THREE.Vector2(uB, vB)],
        [new THREE.Vector2(uB, vA), new THREE.Vector2(uA, vA), new THREE.Vector2(uA, vB)],
        //
        [new THREE.Vector2(uB, vB), new THREE.Vector2(uC, vB), new THREE.Vector2(uC, vC)],
        [new THREE.Vector2(uB, vB), new THREE.Vector2(uC, vC), new THREE.Vector2(uB, vC)],
        //
        [new THREE.Vector2(uB, vB), new THREE.Vector2(uA, vC), new THREE.Vector2(uB, vC)],
        [new THREE.Vector2(uB, vB), new THREE.Vector2(uA, vB), new THREE.Vector2(uA, vC)],
        //
        [new THREE.Vector2(uE, vB), new THREE.Vector2(uD, vC), new THREE.Vector2(uE, vC)],
        [new THREE.Vector2(uE, vB), new THREE.Vector2(uD, vB), new THREE.Vector2(uD, vC)],
        //
        [new THREE.Vector2(uD, vB), new THREE.Vector2(uC, vC), new THREE.Vector2(uD, vC)],
        [new THREE.Vector2(uD, vB), new THREE.Vector2(uC, vB), new THREE.Vector2(uC, vC)],
        //
        [new THREE.Vector2(uB, vC), new THREE.Vector2(uB, vD), new THREE.Vector2(uA, vD)],
        [new THREE.Vector2(uB, vC), new THREE.Vector2(uA, vD), new THREE.Vector2(uA, vC)],
    ]
    //
    let geometry = new THREE.Geometry();
    geometry.vertices = coordinates;
    geometry.faces = faces;
    geometry.faceVertexUvs[0] = uv;
    //
    geometry.uvsNeedUpdate = true;
    geometry.computeFaceNormals();
    geometry.computeBoundingSphere();
    //
    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/dice.jpg");
    let material = new THREE.MeshLambertMaterial({
        color: 0xFFFFFF,
        map: diffuseMap
    });

    let mesh = new THREE.Mesh(geometry, material);
    return mesh
}
/**
 * Animate the model
 */
function animate(step) {
    requestAnimationFrame(function () {
        animate(step);
    });
    step.scene.mesh.rotation.y += 0.003; // add a slow rotation 
    step.controls.update();
    step.renderer.render(step.scene, step.camera);
}
/**
 * Setup the rendering context and build a model
 **/
function init(mesh) {
    //
    renderer = new THREE.WebGLRenderer({
        alpha: true
    });

    let size = Math.min(parent.innerWidth, 512);
    renderer.setSize(size, size);
    let div_container = document.getElementById("container");
    div_container.appendChild(renderer.domElement);
    //
    //  Scene (World, Model)
    //
    scene = new THREE.Scene();
    //
    //  Camera (and OrbitControls)
    //
    camera = new THREE.PerspectiveCamera(
        45, // fov
        512 / 512, // w/h ration
        1, // near cut
        10000 // far cut
    );
    camera.position.set(0, 0, 8);
    camera.lookAt(scene.position);
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    //
    //  Lights
    //  
    let ambient_light = new THREE.AmbientLight(0xFFFFFF);
    scene.add(ambient_light);
    //
    scene.mesh = mesh;
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
 *  Entry function
 */
function main() {
    let step = init(dice());
    animate(step);
}