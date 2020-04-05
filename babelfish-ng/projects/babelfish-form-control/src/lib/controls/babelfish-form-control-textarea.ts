import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormTextarea extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.TEXTAREA, defaultValue, label, validators, asyncValidators);
  }
}
