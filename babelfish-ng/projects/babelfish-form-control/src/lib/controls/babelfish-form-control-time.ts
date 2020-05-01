import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormTime extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.TIME, defaultValue, label, '', validators, controlStyle, asyncValidators);
  }
}
