import {FormControl} from '@angular/forms';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormControl extends FormControl {

  type: BabelfishFormControlTypes;
  label: string;
  isVisible = true;

  constructor(type: BabelfishFormControlTypes, defaultValue, label: string, validators = [], asyncValidators = []) {
    super(defaultValue, validators, asyncValidators);

    this.type = type;
    this.label = label;
  }
}
