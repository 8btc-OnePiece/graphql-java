项目的Readme见fork的[原仓库](https://github.com/graphql-java/graphql-java)
---

## 此fork仓库目前情况
分支：v21.3.Onepiece-10.0.X 由v14.0.Onepiece.10.0.8分支合并tag 21.3而来。

开发流程：
1. 将版本号设置为快照（SNAPSHOT）后缀
2. 变更后本地install，测试通过后，将快照版本deploy到私服
3. 测试环境测试通过后，将版本更新为OnePiece.x+1，deploy到私服
4. 依赖方更新版本号

与原库的21.3相比的主要改动
1. Relay-PageInfo接口增加totalCount，cursorResetDuration字段
2. ExceptionWhileDataFetching扩展:可从异常栈中查找GraphQLError并构建Extensions信息
3. 针对默认参数的情况增加了对@nonNullParam的处理
4. TracingSupport 中去掉list中的值的监控

