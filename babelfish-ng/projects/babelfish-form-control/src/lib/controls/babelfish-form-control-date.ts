import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormDate extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.DATE, defaultValue, label, '', validators, controlStyle, asyncValidators);
  }
}
