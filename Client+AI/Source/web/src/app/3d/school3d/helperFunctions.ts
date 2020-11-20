import {Color} from 'three';

function getColorOfRoomForFilter(value: number, curFilter: string) {
  let percent = 0;
  switch (curFilter) {
    case 'temperature':
      percent = (value - 10) / 20;
      return new Color(percent, 0, (1 - percent));
      break;
    case 'humidity':
      percent = value / 100.0;
      const tmp = 1 - percent;
      return new Color(tmp, tmp, 1);
      break;
    case 'co2':
      if (value > 500) {
        return new Color(1, 1, 0);
        if (value > 800) {
          return new Color(1, 0, 0);
        }
      }
      return new Color(0, 1, 0);
      break;
    case 'light':
      return new Color(1, 1, value < 50 ? 1 : 0);
    case 'volume':
      if (value > 50) {
        if (value > 100) {
          return new Color(1, 0, 0);
        }
        return new Color(1, 1, 0);
      }
      return new Color(0, 1, 0);
      break;
    default:
      console.error('no color fro filter: ', curFilter);
      return new Color(1, 1, 1);
  }
}

export {getColorOfRoomForFilter};
