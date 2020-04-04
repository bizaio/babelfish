import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../types/babelfish-control-types';

export class BabelfishFormDuration extends BabelfishFormControl {

  inputId: string;

  constructor(defaultValue, label: string, validators = [], inputId, asyncValidators = []) {
    super(BabelfishFormControlTypes.DURATION, defaultValue, label, validators, asyncValidators);
    this.inputId = inputId;
  }
}
