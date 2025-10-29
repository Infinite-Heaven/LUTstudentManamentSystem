# 学生成绩管理系统 API 接口文档

## 📋 基础信息

- **基础URL**: `http://localhost:8080`
- **数据格式**: JSON
- **支持跨域**: 是（所有接口已配置 `@CrossOrigin(origins = "*")`）

---

## 📚 目录

1. [用户认证接口](#1-用户认证接口)
2. [课程管理接口](#2-课程管理接口)
3. [选课管理接口](#3-选课管理接口)
4. [成绩管理接口](#4-成绩管理接口)
5. [数据模型](#5-数据模型)

---

## 1. 用户认证接口

**基础路径**: `/user`

### 1.1 用户注册

注册新用户（学生/教师）

**接口地址**: `POST /user/register`

**请求头**:
```
Content-Type: application/json
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |
| role | String | 是 | 用户角色（如：student、teacher、admin） |
| id | int | 否 | 用户ID（系统自动生成） |

**请求示例**:
```json
{
  "username": "zhangsan",
  "password": "123456",
  "role": "student"
}
```

**响应示例**:
```
用户添加成功
```

---

### 1.2 用户登录

用户登录验证

**接口地址**: `GET /user/login`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |

**请求示例**:
```
GET /user/login?username=zhangsan&password=123456
```

**响应示例**:
```
登录成功
```
或
```
用户名或密码错误
```

---

### 1.3 删除用户

管理员删除用户（教师/学生）

**接口地址**: `DELETE /user/delete/{username}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| username | String | 是 | 要删除的用户名 |

**请求示例**:
```
DELETE /user/delete/zhangsan
```

**响应示例**:
```
用户删除成功
```
或
```
用户不存在
```

---

## 2. 课程管理接口

**基础路径**: `/courseManagement`

### 2.1 获取所有课程

获取系统中所有课程列表

**接口地址**: `GET /courseManagement/getAllCourse`

**请求参数**: 无

**请求示例**:
```
GET /courseManagement/getAllCourse
```

**响应示例**:
```json
[
  {
    "id": 1,
    "name": "高等数学",
    "userId": 1,
    "credit": 4.0,
    "teacherId": 101
  },
  {
    "id": 2,
    "name": "Java程序设计",
    "userId": 1,
    "credit": 3.0,
    "teacherId": 102
  }
]
```

---

### 2.2 添加课程

添加新课程

**接口地址**: `POST /courseManagement/addCourse`

**请求头**:
```
Content-Type: application/json
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | String | 是 | 课程名称 |
| userId | int | 是 | 用户ID |
| credit | double | 是 | 学分 |
| teacherId | int | 是 | 教师ID |
| id | int | 否 | 课程ID（系统自动生成） |

**请求示例**:
```json
{
  "name": "数据库系统原理",
  "userId": 1,
  "credit": 3.5,
  "teacherId": 103
}
```

**响应示例**:
```
课程增加成功
```

---

### 2.3 更新课程

更新已存在的课程信息

**接口地址**: `PUT /courseManagement/updateCourse`

**请求头**:
```
Content-Type: application/json
```

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | int | 是 | 课程ID |
| name | String | 是 | 课程名称 |
| userId | int | 是 | 用户ID |
| credit | double | 是 | 学分 |
| teacherId | int | 是 | 教师ID |

**请求示例**:
```json
{
  "id": 1,
  "name": "高等数学（上）",
  "userId": 1,
  "credit": 4.5,
  "teacherId": 101
}
```

**响应示例**:
```
课程修改成功
```

---

### 2.4 删除课程

根据课程名称删除课程

**接口地址**: `DELETE /courseManagement/deleteCourse/{name}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| name | String | 是 | 课程名称 |

**请求示例**:
```
DELETE /courseManagement/deleteCourse/高等数学
```

**响应示例**:
```
课程删除成功
```

---

## 3. 选课管理接口

**基础路径**: `/courseSelect`

### 3.1 选课

学生选择课程

**接口地址**: `PUT /courseSelect/selectOneCourse`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| studentId | Integer | 是 | 学生ID |
| courseId | Integer | 是 | 课程ID |

**请求示例**:
```
PUT /courseSelect/selectOneCourse?studentId=1&courseId=1
```

**响应示例**:
```
选课成功
```
或
```
参数错误：学生ID和课程ID不能为空
```

---

### 3.2 退课

学生退选课程

**接口地址**: `DELETE /courseSelect/deleteCourseSelection/{student_id}/{course_id}`

**路径参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| student_id | Integer | 是 | 学生ID |
| course_id | Integer | 是 | 课程ID |

**请求示例**:
```
DELETE /courseSelect/deleteCourseSelection/1/1
```

**响应示例**:
```
课程删除完毕
```

---

## 4. 成绩管理接口

**基础路径**: `/scoreManagement`

### 4.1 添加成绩

教师为学生录入成绩

**接口地址**: `POST /scoreManagement/addScore`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| StudentId | int | 是 | 学生ID |
| CourseId | int | 是 | 课程ID |
| score | int | 是 | 成绩（0-100之间） |

**请求示例**:
```
POST /scoreManagement/addScore?StudentId=1&CourseId=1&score=85
```

**响应示例**:
```
成绩写入完成
```
或
```
成绩必须在0-100之间
```

---

### 4.2 查询成绩

查询学生的课程成绩

**接口地址**: `GET /scoreManagement/getScore`

**请求参数**:

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| StudentId | int | 是 | 学生ID |
| CourseId | int | 是 | 课程ID |

**请求示例**:
```
GET /scoreManagement/getScore?StudentId=1&CourseId=1
```

**响应示例**:
```
成绩：85
```
或
```
未找到对应的成绩记录
```

---

## 5. 数据模型

### 5.1 User（用户）

```json
{
  "id": 1,
  "username": "zhangsan",
  "password": "123456",
  "role": "student"
}
```

**字段说明**:

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | int | 用户ID（主键，自增） |
| username | String | 用户名（唯一） |
| password | String | 密码 |
| role | String | 用户角色（student/teacher/admin） |

---

### 5.2 Course（课程）

```json
{
  "id": 1,
  "name": "高等数学",
  "userId": 1,
  "credit": 4.0,
  "teacherId": 101
}
```

**字段说明**:

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | int | 课程ID（主键，自增） |
| name | String | 课程名称 |
| userId | int | 用户ID |
| credit | double | 学分 |
| teacherId | int | 教师ID |

---

### 5.3 CourseSelection（选课记录）

**字段说明**:

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | int | 选课记录ID（主键，自增） |
| studentId | int | 学生ID |
| courseId | int | 课程ID |
| score | int | 成绩（-1表示未录入） |

---

## 📝 注意事项

1. **请求编码**: 所有请求均使用 UTF-8 编码
2. **HTTP状态码**: 
   - `200 OK`: 请求成功
   - `400 Bad Request`: 请求参数错误
   - `500 Internal Server Error`: 服务器内部错误
3. **跨域支持**: 所有接口均已配置跨域访问
4. **参数验证**: 
   - 成绩必须在 0-100 之间
   - 必填参数缺失将返回错误信息
5. **错误处理**: 所有接口都会返回相应的错误提示信息，不会返回 `null`

---

## 🔗 接口汇总表

| 功能模块 | 接口路径 | 请求方法 | 功能描述 |
|---------|---------|---------|---------|
| 用户认证 | `/user/register` | POST | 用户注册 |
| 用户认证 | `/user/login` | GET | 用户登录 |
| 用户认证 | `/user/delete/{username}` | DELETE | 删除用户 |
| 课程管理 | `/courseManagement/getAllCourse` | GET | 获取所有课程 |
| 课程管理 | `/courseManagement/addCourse` | POST | 添加课程 |
| 课程管理 | `/courseManagement/updateCourse` | PUT | 更新课程 |
| 课程管理 | `/courseManagement/deleteCourse/{name}` | DELETE | 删除课程 |
| 选课管理 | `/courseSelect/selectOneCourse` | PUT | 学生选课 |
| 选课管理 | `/courseSelect/deleteCourseSelection/{student_id}/{course_id}` | DELETE | 学生退课 |
| 成绩管理 | `/scoreManagement/addScore` | POST | 录入成绩 |
| 成绩管理 | `/scoreManagement/getScore` | GET | 查询成绩 |

---

**文档版本**: v1.0  
**最后更新**: 2024年

