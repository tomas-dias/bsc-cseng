/**
 *  Make a model of the dice with UV mapping
 */
function dice() {
    //
    let coordinates = [
        new THREE.Vector3(-1, -1, -1),
        new THREE.Vector3(-1, -1, +1),
        new THREE.Vector3(+1, -1, +1),
        new THREE.Vector3(+1, -1, -1),
        new THREE.Vector3(+0, +1, +0)

    ];
    //
    let faces = [
        new THREE.Face3(0, 3, 2), new THREE.Face3(2, 1, 0),
        //
        new THREE.Face3(1, 2, 4),
        new THREE.Face3(2, 3, 4),
        new THREE.Face3(3, 0, 4),
        new THREE.Face3(0, 1, 4)

    ]
    
    let geometry = new THREE.Geometry();
    geometry.vertices = coordinates;
    geometry.faces = faces;
    //
    let material = new THREE.MeshLambertMaterial({
        color: 0xF300ED
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