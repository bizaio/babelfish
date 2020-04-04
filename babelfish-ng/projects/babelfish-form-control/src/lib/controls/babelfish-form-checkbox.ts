import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../types/babelfish-control-types';

export class BabelfishFormCheckbox extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.CHECKBOX, defaultValue, label, validators, asyncValidators);
  }
}
