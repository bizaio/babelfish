import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormDuration extends BabelfishFormControl {

  constructor(defaultValue, label: string, tooltip: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.DURATION, defaultValue, label, tooltip, validators, controlStyle, asyncValidators);
  }
}
