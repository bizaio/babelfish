import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormCheckbox extends BabelfishFormControl {

  constructor(defaultValue, label: string, tooltip: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.CHECKBOX, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);
  }
}
