import {BabelfishFormControl} from './babelfish-form-control';
import {BabelfishFormControlTypes} from '../babelfish-form-control.component';

export class BabelfishFormDateTime extends BabelfishFormControl {

  constructor(defaultValue, label: string, validators = [], controlStyle: string = "", asyncValidators = []) {
    super(BabelfishFormControlTypes.DATETIME, defaultValue, label, '', validators, controlStyle, asyncValidators);
  }
}
