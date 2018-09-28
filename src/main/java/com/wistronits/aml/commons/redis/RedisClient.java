//
//package com.wistronits.aml.commons.redis;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wistronits.aml.commons.util.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.util.SafeEncoder;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * @Author 10376
// * @Date 2018/3/17
// */
//
//@Component
//public class RedisClient {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Value("${redisServerIp}")
//	private String HOST;
//	@Value("${redisServerPort}")
//	private int PORT;
//
//	private static JedisPool pool = null;
//
//	public void initRedis() {
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(128);
//		config.setMaxIdle(80);
//		config.setMaxWaitMillis(2001);
//
//		pool = new JedisPool(config, HOST, PORT, 2000);
//	}
//
//	/**
//	 * 把key存入redis中
//	 *
//	 * @param key k
//	 * @param value v
//	 * @param seconds 过期时间（秒）
//	 * @return boolean
//	 */
//	public boolean set(byte[] key, byte[] value, int seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			String result = jedis.set(key, value);
//			if (seconds > 0) {
//				Long r = jedis.expire(key, seconds);
//			}
//		} catch (Exception e) {
//			return false;
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return true;
//	}
//
//	public byte[] get(byte[] key) {
//		byte[] value = null;
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			value = jedis.get(key);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return value;
//	}
//
//	/**
//	 * 向缓存中设置对象
//	 *
//	 * @param key key
//	 * @param obj value
//	 * @return boolean
//	 */
//	public boolean set(String key, Object obj, Integer seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			ObjectMapper mapper = new ObjectMapper();
//			String value = mapper.writeValueAsString(obj);
//			jedis.set(SafeEncoder.encode(key), SafeEncoder.encode(value));
//			if (seconds != null) {
//				jedis.expire(key, seconds);
//			}
//			return true;
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * 向缓存中设置对象
//	 *
//	 * @param key key
//	 * @param value value
//	 * @return boolean
//	 */
//	public boolean set(String key, String value, Integer seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			jedis.set(SafeEncoder.encode(key), SafeEncoder.encode(value));
//			if (seconds != null) {
//				jedis.expire(key, seconds);
//			}
//			return true;
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * 移除缓存中设置对象
//	 *
//	 * @param keys 被删除的KEYS
//	 * @return Long 被删除个数
//	 */
//	public Long del(String... keys) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.del(keys);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 根据key 获取对象
//	 *
//	 * @param key key
//	 * @return T
//	 */
//	public <T> T get(String key, Class<T> clazz) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			String v = jedis.get(key);
//			if (StringUtils.isNotEmpty(v)) {
//				ObjectMapper mapper = new ObjectMapper();
//				return mapper.readValue(v, clazz);
//			}
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 根据key值得到String类型的返回值
//	 *
//	 * @param key key
//	 * @return String
//	 */
//	public String get(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			String v = jedis.get(key);
//			if (StringUtils.isNotEmpty(v)) {
//				return v;
//			}
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	public Boolean exists(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.exists(key);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * redis的list操作： 把元素插入到列表的尾部
//	 *
//	 * @param key KEY
//	 * @param strings 要插入的值，变参
//	 * @return 返回插入后list的大小
//	 */
//	public Long rpush(String key, String... strings) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.rpush(key, strings);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * redis的list操作： 根据开始与结束下标取list中的值
//	 *
//	 * @param key KEY
//	 * @param start 开始下标
//	 * @param end 结束下标
//	 * @return List<String>
//	 */
//	public List<String> lrange(String key, int start, int end) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.lrange(key, start, end);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * redis的list操作： 取列表的长度
//	 *
//	 * @param key key
//	 * @return Long
//	 */
//	public Long llen(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.llen(key);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * redis的list操作： 根据值移除list中的元素
//	 *
//	 * @param key KEY
//	 * @param count ： count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 count < 0 :
//	 *            从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 count = 0 : 移除表中所有与
//	 *            value 相等的值。
//	 * @param value 要删除的值
//	 * @return 返回被移除的个数
//	 */
//	public Long lrem(String key, long count, String value) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.lrem(key, count, value);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//
//
//	public boolean setLong(String key, Long value) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return "OK".equals(jedis.set(key, String.valueOf(value)));
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return false;
//	}
//
//	public Long getLong(String key) {
//		String result = get(key);
//		return result == null ? null : Long.valueOf(result);
//	}
//
//	public Long incrBy(String key, Long increment) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.incrBy(key, increment);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	public Long hashSet(String key, String field, String value) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hset(key, field, value);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return -1L;
//	}
//
//	public Long hashSetLong(String key, String field, Long value) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hset(key, field, String.valueOf(value));
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return -1L;
//	}
//
//	public Long hashIncrBy(String key, String field, Long increment) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hincrBy(key, field, increment);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return -1L;
//	}
//
//	public Map<String, String> hashGetAll(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hgetAll(key);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	public Set<String> hashKeys(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hkeys(key);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	public Long hashDelAll(String key, String... fields) {
//		Jedis jedis = null;
//		try {
//			jedis = pool.getResource();
//			return jedis.hdel(key, fields);
//		} catch (Exception e) {
//		} finally {
//			if (null != jedis) {
//				jedis.close();
//			}
//		}
//		return null;
//	}
//
//	public final static String VIRTUAL_COURSE_PREX = "_lc_vc_";
//
//	/**
//	 * 得到Key
//	 *
//	 * @param key
//	 * @return
//	 */
//	public String buildKey(String key) {
//		return VIRTUAL_COURSE_PREX + key;
//	}
//
//	/**
//	 * 设置 list
//	 *
//	 * @param <T>
//	 * @param key
//	 */
//	public <T> void setList(String key, List<T> list) {
//		try {
//			pool.getResource().set(key.getBytes(), ObjectTranscoder.serialize(list));
//		} catch (Exception e) {
//			logger.error("Set key error : " + e);
//		}
//	}
//
//	/**
//	 * 获取list
//	 *
//	 * @param <T>
//	 * @param key
//	 * @return list
//	 */
//	public <T> List<T> getList(String key) {
//		String bKey = buildKey(key);
//		if (pool.getResource() == null || !pool.getResource().exists(key.getBytes())) {
//			return null;
//		}
//		byte[] in = pool.getResource().get(key.getBytes());
//		List<T> list = (List<T>) ObjectTranscoder.deserialize(in);
//		return list;
//	}
//
//	/**
//	 * 设置 map
//	 *
//	 * @param <T>
//	 * @param key
//	 */
//	public <T> void setMap(String key, Map<String, T> map) {
//		try {
//			pool.getResource().set(key.getBytes(), ObjectTranscoder.serialize(map));
//		} catch (Exception e) {
//			logger.error("Set key error : " + e);
//		}
//	}
//
//	/**
//	 * 获取list
//	 *
//	 * @param <T>
//	 * @param key
//	 * @return list
//	 */
//	public <T> Map<String, T> getMap(String key) {
//		String bKey = buildKey(key);
//		if (pool.getResource() == null || !pool.getResource().exists(key.getBytes())) {
//			return null;
//		}
//		byte[] in = pool.getResource().get(key.getBytes());
//		Map<String, T> map = (Map<String, T>) ObjectTranscoder.deserialize(in);
//		return map;
//	}
//}