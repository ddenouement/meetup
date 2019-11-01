import { NgModule } from '@angular/core';
// @ts-ignore
import { Smile } from 'angular-feather/icons';
// @ts-ignore
import {FeatherModule} from "angular-feather";


const icons={Smile};
@NgModule({
 imports: [FeatherModule.pick(icons)],
  exports: [FeatherModule]
})
export class IconsModule { }

