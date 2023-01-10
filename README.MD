# ExcelConversion
支持xml转excel、js转excel、json转excel、excel转xml、excel转js、excel转json、js转json

## 应用场景：
新词条库系统导出的文件格式为excel，无法直接作为android端词条文件使用，且导出文件包含某机型下所有词条(WEB、LCD)，没有进行区分。
需要一个工具，实现导出excel转换为词条文件(js格式、json格式、xml格式)，并替换到各模块下；同时也需要将各模块下的词条文件转换成excel格式，导入到词条库系统中。

## 使用方法
本工具使用命令行运行  

以下Excel均指新词条库系统导出的文件，导出后需要先将xlsx转为xls格式

android端词条转Excel——xml(strings、arrays)转Excel(xls)
${inputList[@]}为两两一组的strings.xml，分别是英文翻译xml和对应某语言翻译xml，如values/strings.xml、values-it-rIT/strings.xml;
```shell
java -jar ExcelConversion.jar -t xmlListToXls -i ${inputList[@]} -o output.xls
```

Excel转android端词条——Excel(xls)转xml(strings、arrays)
$2为Excel文件的路径，${inputList[@]}为英文词条的路径列表，如A/values/strings.xml、B/values/strings.xml，所生成的某语言翻译xml文件在A/values/strings_change.xml、B/values/strings_change.xml
```shell
java -jar ExcelConversion.jar -t xlsToXmlList -i $2 -o ${inputList[@]}
```

web(旧)词条转Excel——js转Excel(xls)
${inputList[@]}为两两一组的js文件，分别是英文翻译js和对应某语言翻译js，如A/ENGLISH.js、A/CHINESE_T.js
```shell
java -jar ExcelConversion.jar -t jsListToXls -i ${inputList[@]} -o output.xls
```

Excel转web(旧)词条——Excel(xls)转js
$2为Excel文件的路径，${inputList[@]}为英文词条的路径列表，如A/ENGLISH.js、B/ENGLISH.js，所生成的某语言翻译js文件在A/LANG_CHANGE.js、B/LANG_CHANGE.js
```shell
java -jar ExcelConversion.jar -t xlsToJsList -i $2 -o ${inputList[@]}
```

web(新)词条转Excel——json转Excel(xls)
${inputList[@]}为两两一组的json文件，分别是英文翻译json和对应某语言翻译json，如A/ENGLISH.json、A/CHINESE_T.json
```shell
java -jar ExcelConversion.jar -t jsonListToXls -i ${inputList[@]} -o output.xls
```

Excel转web(新)词条——Excel(xls)转json
$2为Excel文件的路径，${inputList[@]}为英文词条的路径列表，如A/ENGLISH.json、B/ENGLISH.json，所生成的某语言翻译js文件在A/LANG_CHANGE.json、B/LANG_CHANGE.json
```shell
java -jar ExcelConversion.jar -t xlsToJsonList -i $2 -o ${inputList[@]}
```

Js词条转Json词条
${inputList[@]}为js文件的路径列表，所转换的json文件将在js同级目录下，命名为LAN_i.json
```shell
java -jar ExcelConversion.jar -t jsToJson - i ${inputList[@]}
```

## 命令行说明  

  -t 判断输入的类型 取值如下  
    xmlListToXls
    xlsToXmlList 
    jsListToXls
    xlsToJsList
    jsonListToXls
    xlsToJsonList
    jsToJson
 -i 输入的文件  
 -o 输出的文件  
 -coding 文件的文本编码 默认utf-8  

