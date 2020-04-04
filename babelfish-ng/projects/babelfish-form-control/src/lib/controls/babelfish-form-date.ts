import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../types/babelfish-control-types';

export class BabelfishFormDate extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], asyncValidators = []) {
    super(BabelfishFormControlTypes.DATE, defaultValue, label, validators, asyncValidators);
  }
}
