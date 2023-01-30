项目的Readme见fork的[原仓库](https://github.com/graphql-java/graphql-java)
---

## 此fork仓库目前情况
分支：v14.0.Onepiece.10.0.x 管理基于v14.0的版本变更，x为每次变更的版本号，从1开始，但因为曾经的一次版本膨胀，现在到了v14.0.OnePiece.10.0.7。
开发流程：
1. 将版本号设置为快照（SNAPSHOT）后缀
2. 变更后本地install，测试通过后，将快照版本deploy到私服
3. 测试环境测试通过后，将版本更新为OnePiece.x+1，deploy到私服
4. 依赖方更新版本号

暂未合入v14.0.Onepiece.10.0.x的其他分支:
* v14.0.OnePiece.9 字段有值后不走resolver [@caoly](https://github.com/Changshengsheng)
* 14.0.Onepiece.10.0.6 一些内存优化 [@caoly](https://github.com/Changshengsheng)
* v1.3.1.OnePiece.3 枚举支持生成code值? 大量未合入的commit [@zhoulk](https://github.com/estal011) [@pengjh](https://github.com/cunshu1)

## 版本变更信息
### 对不上版本号了
1. Relay扩展:14.0中，Relay-PageInfo接口增加totalCount字段
2. CacheControl扩展:response.extensions中增加cacheControl信息
3. ExceptionWhileDataFetching扩展:可从异常栈中查找GraphQLError并构建Extensions信息
4. 针对默认参数的情况增加了对@nonNullParam的处理
5. DataFetchingEnvironmentImpl.getArguments()去掉了不可变的包装
### 14.0.OnePiece.10.0.5 
TracingSupport 中去掉list中的值的监控
### 14.0.OnePiece.10.0.7
PageInfo接口增加cursorResetDuration字段
### 14.0.OnePiece.10.0.8
graphql directive 基本类型参数设置默认值及生效-目前只支持Int、Long、String、Float、BigDecimal、Boolean，如下：
```
directive @Size(min: Int = 1, max : Int = 5000, message : String = "graphql.validation.Size.message",inclusive : Boolean! = true,amount: Float = 1.0,long: Long = 1,bigDecimal: BigDecimal="1.0")
on ARGUMENT_DEFINITION | INPUT_FIELD_DEFINITION
```
- 设置基本类型默认值报错，如:Expected type 'Int' but was 'IntValue'，具体查看Scalars类
- 默认值生效处理查看：DataFetchingEnvironmentImpl#filedSupplementDirectiveArguments

## todo
* 将15.0的变更合入v14.0.Onepiece.10.0.x
* 更新到最新的稳定版

