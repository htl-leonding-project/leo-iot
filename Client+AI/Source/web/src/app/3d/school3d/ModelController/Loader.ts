import {LoadingManager} from 'three';
import {MTLLoader} from 'three/examples/jsm/loaders/MTLLoader';
import {OBJLoader} from 'three/examples/jsm/loaders/OBJLoader';
import {Scene} from 'three';
import {ModelController} from './modelController';


export class Loader {

  constructor(private modelName: String,
              private scene: Scene,
              private objects,
              private objectArr,
              private floors,
              private finishLoadingCallback,
              private floorObject
  ) {
  }


  generateLoadingManager(): LoadingManager {
    const loadingManager = new LoadingManager();
    loadingManager.onStart = (url, itemsLoaded, itemsTotal) =>
      console.log('Started loading file: ' + url + '.\nLoaded ' + itemsLoaded + ' of ' + itemsTotal + ' files.');

    loadingManager.onError = (url) =>
      console.log('There was an error loading ' + url);

    loadingManager.onLoad = () => {
      this.finishLoadingCallback();
      ModelController.instance.roomObject = this.objectArr.filter(el => el.name[0] === 'U' || el.name[0] === '1'
        || el.name[0] === '2' || el.name[0] === 'E');
      this.floorObject = this.objectArr.filter(el => el.name[0] === 'c' || el.name[0] === 's'
        || el.name[0] === 'f' || el.name[0] === 'g');
      // applyFilter('basic');
      console.log('Loading Complete');
    };

    loadingManager.onProgress = (url, itemsLoaded, itemsTotal) => {
      console.log('Loading file: ' + url + '.\nLoaded ' + itemsLoaded + ' of ' + itemsTotal + ' files.');
    };
    return loadingManager;
  }

  loadAssets() {
    const loadingManager = this.generateLoadingManager();

    const mtlLoader = new MTLLoader();
    mtlLoader.setPath('/assets/models/');
    const modelUrl = this.modelName + '.mtl';
    mtlLoader.load(modelUrl, (materials) => {
      materials.preload();

      const objLoader = new OBJLoader(loadingManager);
      objLoader.setMaterials(materials);
      objLoader.setPath('/assets/models/');
      objLoader.load(this.modelName + '.obj', (object) => {
        this.scene.add(object);
        for (const o of object.children) {
          this.objectArr.push(o);
          if (this.floors.includes(o.name)) {
            o.castShadow = true;
          } else {
            o.receiveShadow = true;
          }
        }
        this.scene.traverse(children => this.objects.push(children));
      });
    });

  }

}


