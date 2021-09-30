import {Color, DirectionalLight, HemisphereLight, PCFShadowMap, PerspectiveCamera, Scene, WebGLRenderer} from 'three';
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';
import {BASE_COLOR_HEX} from '../colors';

function setUpScene(): Scene {
  const scene = new Scene();
  scene.background = new Color(0x1b2023);
  return scene;
}

function setUpHemiLight(scene) {
  const hemiLight = new HemisphereLight(BASE_COLOR_HEX, BASE_COLOR_HEX, 0.4);
  hemiLight.position.set(0, 500, 0);
  scene.add(hemiLight);
}

function setupDirLight(scene) {
  const dirLight = new DirectionalLight(BASE_COLOR_HEX, 0.8);
  dirLight.position.set(-1, 0.75, 1);
  dirLight.position.multiplyScalar(50);
  dirLight.name = 'dirLight';

  scene.add(dirLight);

  dirLight.castShadow = true;
  dirLight.shadow.mapSize.height = dirLight.shadow.mapSize.height = 1024 * 2;
  const d = 300;
  dirLight.shadow.camera.left = -d;
  dirLight.shadow.camera.right = d;
  dirLight.shadow.camera.top = d;
  dirLight.shadow.camera.bottom = -d;

  dirLight.shadow.camera.far = 3500;
  dirLight.shadow.bias = -0.0001;
}

export function getSetupScene() {
  const scene = setUpScene();
  setUpHemiLight(scene);
  setupDirLight(scene);
  return scene;
}

export function setupRenderer(window): WebGLRenderer {
  const renderer = new WebGLRenderer({antialias: true});
  renderer.setPixelRatio(window.devicePixelRatio);
  renderer.setSize(window.innerWidth, window.innerHeight);
  renderer.shadowMap.enabled = true;
  renderer.shadowMap.type = PCFShadowMap;
  return renderer;
}

export function setupCamera(window): PerspectiveCamera {
 const camera = new PerspectiveCamera(50, window.innerWidth / window.innerHeight, 1, 25000);
  camera.position.set(8000, 2900, 7000);
  return camera;
}

export function setupController(controls): OrbitControls {
  controls.autoRotateSpeed = 0.5;
  controls.enableDamping = true;
  controls.dampingFactor = 0.25;
  controls.screenSpacePanning = false;
  controls.minDistance = 10;
  controls.maxDistance = 15000;
  controls.maxPolarAngle = Math.PI / 2;
  return controls;
}
