### 手机号注册 POST /user/phoneRegister
- 参数
  - phone 手机号
  - password 密码 需要前端MD5加密
  - verify_code 短信验证码 非必填
  - invitor_id 邀请人ID 非必填
  - username 用户昵称
- 返回值
- code(200表示正常读取data内容，非200则表示失败读取message失败信息)
- message
- 字段说明
- 限定访问间隔时间
- 1000毫秒
- 实例
- `curl -X POST http://localhost:9001/user/phoneRegister -d 'phone=&password=&verify_code=&invitor_id=&username='` 

### 手机号登录 POST /user/phoneLogin
- 参数
  - phone 手机号
  - password 密码 需要前端MD5加密
- 返回值
- code(200表示正常读取data内容，非200则表示失败读取message失败信息)
- message
- 字段说明
    - token 用户token
- 限定访问间隔时间
- 1000毫秒
- 实例
- `curl -X POST http://localhost:9001/user/phoneLogin -d 'phone=&password='` 