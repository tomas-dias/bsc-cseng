/**
 *  Make a model of the Earth
 */
function earth() {
    //
    let geometry = new THREE.SphereGeometry(3, 32, 32);
    //
    
    let loader = new THREE.TextureLoader();
    let diffuseMap = loader.load("media/padrao_1.png");
    //let specularMap = loader.load("media/earth_specular.jpg");
    //let normalMap = loader.load("media/earth_normals.jpg");
    let material = new THREE.MeshPhongMaterial({
        color: 0xFFFFFF,
        specular: 0xFFFFFF,
        map: diffuseMap,
        //specularMap: specularMap,
        //normalMap: normalMap
    });
    
    //let material = new THREE.MeshLambertMaterial({ color: "red" });

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
    step.scene.mesh.rotation.y += 0.005;
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
        45, // abertura
        512 / 512, // proporção largura/altura
        1, // corte perto
        10000 // corte longe
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
    let sun = new THREE.PointLight(0xffffff, 3, 450);
    sun.position.set(-300, 0, 300);
    scene.add(sun);
    //
    //
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
    let step = init(earth());
    animate(step);
}