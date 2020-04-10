import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormInputMulti extends BabelfishFormControl {
  constructor(defaultValue, label: string, tooltip: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.INPUT_MULTI, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);
  }
}
