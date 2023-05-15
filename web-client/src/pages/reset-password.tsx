// first checks with the backend if code from link user clicked and email query parameters are correct by hitting /reset/login/password/verify?email=email&code=code to request
// if correct send request to /reset/login/password with password as body

import { ResetPassword } from "../modules/reset-password/ResetPassword";

export default ResetPassword;