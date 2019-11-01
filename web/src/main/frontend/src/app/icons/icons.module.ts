import { NgModule } from '@angular/core';
import { Smile } from 'angular-feather/icons';
import {FeatherModule} from "angular-feather";


const icons={Smile};
@NgModule({
 imports: [FeatherModule.pick(icons)],
  exports: [FeatherModule]
})
export class IconsModule { }

