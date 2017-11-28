<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${clazz.packagename}.mapper.${clazz.classname}Mapper">
	<sql id="Base_Column_List">
		${insertField}
	</sql>
	
	<insert id="add" parameterType="${clazz.packagename}.model.${clazz.classname}">
		insert into ${tableName}(${insertField})
		values(${insertValue})
	</insert>
	
	<delete id="del">
		delete from ${tableName} where id in 
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
			${delWhere}
		</foreach> 
	</delete>
	
	<update id="update" parameterType="${clazz.packagename}.model.${clazz.classname}">
		update ${tableName} 
		<set>${updateField}
		</set> 
		,modify_date=now() where id=${byId}
	</update>
	
	<select id="queryPage" parameterType="vo" resultType="${clazz.packagename}.model.${clazz.classname}">
		select 
		<include refid="Base_Column_List" /> 
		from ${tableName} 
		<where>
			<if test="keywords!=null and keywords!=''">name like ${keywords}</if>
		</where>
		<choose>
			<when test="orderField !=null and orderField !=''">
				 order by ${orderField} 
				 <if test="orderDirection != null and orderDirection != ''">${orderDirection}</if>
			</when>
			<otherwise>
				 order by create_date desc 
			</otherwise>
		</choose>
	</select>
	
	<select id="queryById" parameterType="String" resultType="${clazz.packagename}.model.${clazz.classname}">
		select 
		<include refid="Base_Column_List" /> 
		from ${tableName} where id=${byId}
	</select>
	
</mapper>