import {Pipe} from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'fromNow'
})
export class FromNowPipe {
  transform(value) {
    if (value && (value instanceof Date ||
      typeof value === 'number')) {
      return   moment(value).fromNow();
    }
  }
}
