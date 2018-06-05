/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.property;

import java.util.Iterator;

/**解析类似于orders[0].items[0].name,该类继承Iterator接口,可迭代处理嵌套多春风表达式
 * @author Clinton Begin
 */
public class PropertyTokenizer implements Iterable<PropertyTokenizer>, Iterator<PropertyTokenizer> {
  private String name;// 当前表达式名称
  private String indexedName;// 当前表达式索引名
  private String index;// 索引下标
  private String children;// 子表达式

  public PropertyTokenizer(String fullname) {
    int delim = fullname.indexOf('.');// 查找.位置
    if (delim > -1) {
      name = fullname.substring(0, delim);// 初始化name
      children = fullname.substring(delim + 1);//初始化children
    } else {
      name = fullname;
      children = null;
    }
    indexedName = name;
    delim = name.indexOf('[');
    if (delim > -1) {
      index = name.substring(delim + 1, name.length() - 1);
      name = name.substring(0, delim);
    }
  }

  public String getName() {
    return name;
  }

  public String getIndex() {
    return index;
  }

  public String getIndexedName() {
    return indexedName;
  }

  public String getChildren() {
    return children;
  }

  @Override
  public boolean hasNext() {
    return children != null;
  }
  /**创建新的PropertyTokenizer对象并解析children字段记录的子表达式*/
  @Override
  public PropertyTokenizer next() {
    return new PropertyTokenizer(children);
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Remove is not supported, as it has no meaning in the context of properties.");
  }

  @Override
  public Iterator<PropertyTokenizer> iterator() {
    return this;
  }
}
