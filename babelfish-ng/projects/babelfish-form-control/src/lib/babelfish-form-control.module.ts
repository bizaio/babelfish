import {Inject, ModuleWithProviders, NgModule, Optional, SkipSelf} from '@angular/core';
import {BabelfishFormControlComponent} from './babelfish-form-control.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  CalendarModule,
  CheckboxModule, ChipsModule,
  DropdownModule,
  InputSwitchModule, ListboxModule, MultiSelectModule,
  OverlayPanelModule,
  TooltipModule
} from 'primeng';
import {DurationPickerModule} from 'ngx-duration-picker';
import {BabelfishDurationPickerComponent} from './duration-picker/babelfish-duration-picker.component';

@NgModule({
  declarations: [BabelfishFormControlComponent, BabelfishDurationPickerComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CheckboxModule,
    CalendarModule,
    DropdownModule,
    DurationPickerModule,
    OverlayPanelModule,
    FormsModule,
    TooltipModule,
    InputSwitchModule,
    ChipsModule,
    MultiSelectModule,
    ListboxModule
  ],
  exports: [BabelfishFormControlComponent]
})
export class BabelfishFormControlModule {}
