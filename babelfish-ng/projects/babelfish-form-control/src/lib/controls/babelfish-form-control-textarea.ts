import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormTextarea extends BabelfishFormControl {

  constructor(defaultValue, label: string, tooltip: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.TEXTAREA, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);
  }
}
