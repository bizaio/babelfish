import { NgModule } from '@angular/core';
import { BabelfishFormControlComponent } from './babelfish-form-control.component';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {CalendarModule, CheckboxModule} from 'primeng';

@NgModule({
  declarations: [BabelfishFormControlComponent],
  imports: [
    CalendarModule,
    DropdownModule,
    CommonModule,
    CheckboxModule,
    ReactiveFormsModule
  ],
  exports: [BabelfishFormControlComponent]
})
export class BabelfishFormControlModule { }
