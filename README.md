# Internzz
<h2>我在大三暑假前投递了成都星宇科技公司，先给了一道笔试题。设计一个简单Java Web程序。</h1>
<p>题目要求：</p>
<p>一、基础信息： </p>
<p>1、有两张数据表</p>
<p>部门（名称，编号，办公地址）；</p>
<p>员工（姓名，工号，联系电话，入职时间，职级）；</p>

<p>2、假设当前有5个部门，编号分别为A, B, C, D, E，每个部门的评级标准各自独立，同时各个部门的评级标准可以各自改动，增加新的考核项。当前各部门评级标准如下（初始职级为1）：</p>
<p>部门A: 入职满1年晋升1级；</p>
<p>部门B: 入职满2年晋升1级；</p>
<p>部门C: 入职满2年晋升1级；</p>
<p>部门D: 入职满3年晋升1级；</p>
<p>部门E: 入职满5年晋升1级；</p>

<p>备注：可能的变动实例，如部门A评级标准增加“职级大于3级后满2年晋升1级”，公司融资成功准备新设立2个部门等（即评级逻辑应该在不同的类中实现）。</p>

<p>请设计一个基于SpringMVC+Mybatis（或者 hibernate）的Web程序，数据库用MySQL，工程用maven构建，实现如下功能：</p>
<p>1、每天凌晨1点自动检测员工信息，判断是否晋升级别，完成级别评定；</p>
<p>2、提供RESTful API，查询所有员工基本信息，按部门分组，按级别从大到小排序，返回JSON数据结构类似：</p>
<p>{                               </p>
<p>  "data": [                   </p>
<p>    {                          </p>
<p>      "部门名称": "部门A",     </p>
<p>     "部门编号": "A",         </p>
<p>      "员工": [               </p>
<p>        {                     </p>
<p>          "姓名": "徐某",</p>
<p>          "电话": "13132056735",
<p>          "入职时间": "2010-01-01",
<p>          "职级": 7
<p>        },
<p>        {
<p>          "姓名": "张某",
<p>          "电话": "13232056735",
<p>          "入职时间": "2011-01-01",
<p>          "职级": 6
<p>        }
<p>      ]
<p>    },
<p>    {
<p>      "部门名称": "部门B",
<p>      "部门编号": "B",
<p>      "员工": [
<p>        {
<p>          "姓名": "刘某",
<p>          "电话": "13332056735",
<p>          "入职时间": "2013-01-01",
<p>          "职级": 2
<p>        }
<p>      ]
<p>    }
<p>  ],
<p>  "status": "success"
<p>}

<p>二、要求：
<p>1、设计程序完成全部功能；
<p>2、提供设计文档，简单描述设计关键点。

<h2>功能：</h2>
<p>最后我设计出的这个程序一共有4个功能：
<p>1、实现了每天1点检测出符合升职条件的人员，并对其进行升职，将完成升职的人员信息显示在前台。如当天无人升职，则在前台显示“今天无人升职”。
<p>2、设置了一个文本框，输入要查看的部门编号，鼠标离开焦点，自动刷新出该部门的人员信息，并按职级降序显示在前台。如果输入了不存在的部门编号，则前台显示出红色的“没有该部门”的信息。
<p>3、完成了一个Restful风格的API，输出部门编号，查找部门的人员信息（通过EL表达式显示）。
<p>4、完成了一个Restful风格的API，输出员工姓名关键字，通过模糊查找，查找出符合条件的人员信息（通过EL表达式显示）。

<h2>设计文档：</h2>
<p>为了能更好的实现评级功能，我在题目原有的数据库上加了点改动。
<p>员工表增加了一个升职日期字段；并增添了一张新表（评级表）
 
<p>设计思路：
	<p>增加评级表（Rating）是因为题目要求可以增加新的考核项，即一个部门可能会有多种不同的升职条件。于是我把评级表设为多的一方（previous_level：在这个级别以上的员工使用这条标准，requirement：升职要求（单位：天数）， department_id作为外键）。
	<p>增加升职日期字段是因为，我在最开始做这个功能的时候，hql语句是：
from Employee a where ( CURRENT_DATE() - a.entry_time ) > ANY"
		+ "(select b.requirements from Rating b where a.department.id = b.department.id)"。
<p>然后我发现，这样的话每天都会对升级了的人再进行升级，因为entry_time是固定不变的，所以如果升职了一次后，CURRENT_DATE() - a.entry_time会永远大于升职要求。于是我增加了一个升职日期字段，即升职当天的日期，默认初始值为入职日期。于是接下来我使用这条hql语句：
from Employee a where ( CURRENT_DATE() - a.promoted_time ) > ANY"
		+ "(select b.requirements from Rating b where a.department.id = b.department.id)
<p>然后再对筛选出来的员工们进行level字段+1，及设置promoted_time字段为当前日期。这样就相当于，升职时把promoted_time值清零，就不会出现之前的那种情况了。
<p>前端我用setInterval设置了一个60秒的计时器，判断条件当时间为1点时，执行升职任务函数。并将升职人员的信息显示在前台，如果当天无人升职则显示无人升职。
（遗憾的是，我到现在还没想出来怎么操作判断不同级别的人参照不同的升职条件。。。。。。希望之后面试官老师能够给我点提示，感激不尽。）

<p>第二个功能的话，我在id=text1 标签上设置了 onblur="checkDepID();getItem();"。
<p>checkDepID()通过ajax检测是否输入了正确的部门编号，如果输出的部门编号不存在的话会显示“没有该部门”。
<p>getItem()是获取了id=text1标签输入的内容，然后返回后台查找相应部门的员工，并通过json格式返回。返回成功的话会调用display(data)函数，将返回的数据通过html拼接输出在div id= allEmployeeContent上。
<p>但是测试的时候发现了尴尬的一个地方。如果输出了不存在的部门ID，虽然页面正确显示了“没有该部门”，程序也仍然在跑。但是控制台在报错，原因是因为display(data)函数仍在输出返回的data数据，但是这个数据里面是空的，报错显示我角标越界了。
<p>之后想了一个办法，即加一个button控制该text。如果没有这个部门的话，给button设置一个disabled，当data有值时再取消disabled。
<p>不过这个办法好像没有正面解决这个问题，所以我暂时还没有这样修改，还在想有没有别的方法。
<p>实现RESTful风格我使用在了后面我加了两个功能上了。一个是按姓名模糊查找，一个是按部门编号查找（这个用的EL表达式显示）。

<h2>总结：</h2>	
<p>虽然说这次做的这个Web程序功能不多，但也让我收获了不少
<p>如增强了我使用jquery，ajax和json的能力。
<p>学会了使用RESTful风格的API的写法。
<p>让我学会了使用一些较为更复杂的hql语句
<p>但还是有很多不足的地方。加油吧！！！！！！！！！！
