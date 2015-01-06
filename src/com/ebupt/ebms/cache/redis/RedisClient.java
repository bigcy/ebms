package com.ebupt.ebms.cache.redis;

public class RedisClient {

	private ConnectionPool pool = null;

	private Transcoder trans = new ObjectSerialzeTranscoder();

	public RedisClient() {
		this.pool = ConnectionPool.getInstance();
	}

	public Object getObject(String key) {
		byte[] value = get(key);

		return trans.decode(value);
	}

	public byte[] get(String key) {
		Connection con = pool.get();
		byte[] value = null;
		try {
			value = con.getClient().get(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				value = con.getClient().get(key.getBytes());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
		return value;
	}

	public byte[] lpop(String key) {
		Connection con = pool.get();
		byte[] value = null;
		try {
			value = con.getClient().lpop(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				value = con.getClient().lpop(key.getBytes());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
		return value;
	}

	public Object lpopObject(String key) {
		Connection con = pool.get();
		byte[] value = null;
		try {
			value = con.getClient().lpop(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				value = con.getClient().lpop(key.getBytes());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
		if (value != null) {
			return trans.decode(value);
		} else {
			return null;
		}
	}

	public void rpush(String key, byte[] value) {
		Connection con = pool.get();
		try {
			con.getClient().rpush(key.getBytes(), value);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().rpush(key.getBytes(), value);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}

	public void rpush(String key, Object obj) {
		byte[] value = trans.encode(obj);
		Connection con = pool.get();
		try {
			con.getClient().rpush(key.getBytes(), value);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().rpush(key.getBytes(), value);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}

	public void rpush(String key, String value) {
		Connection con = pool.get();
		try {
			con.getClient().rpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().rpush(key, value);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}

	public void setString(String key, String value) {
		Connection con = pool.get();
		try {
			con.getClient().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().set(key, value);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}

	public String getString(String key) {
		Connection con = pool.get();
		String value = null;
		try {
			value = con.getClient().get(key);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				value = con.getClient().get(key);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
		return value;
	}

	public void setObject(String key, Object value) {
		byte[] bv = trans.encode(value);
		set(key, bv);
	}

	public void setObject(String key, Object value, int second) {
		byte[] bv = trans.encode(value);
		set(key, bv, second);
	}

	public void set(String key, byte[] value) {
		Connection con = pool.get();
		try {
			con.getClient().set(key.getBytes(), value);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().set(key.getBytes(), value);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}

	public void set(String key, byte[] value, int second) {
		set(key, value);
		expire(key, second);
	}

	public long expire(String key, int second) {
		Connection con = pool.get();
		try {
			return con.getClient().expire(key, second);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				return con.getClient().expire(key, second);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
			pool.put(con);
		}
	}

	public boolean exists(String key) {
		Connection con = pool.get();
		try {
			return con.getClient().exists(key);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				return con.getClient().exists(key);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			pool.put(con);
		}
	}

	public long delete(String key) {
		Connection con = pool.get();
		try {
			return con.getClient().del(key);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				return con.getClient().del(key);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
			pool.put(con);
		}
	}

	public boolean ltrim(String key, long start, long end) {
		Connection con = pool.get();
		try {
			con.getClient().ltrim(key, start, end);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().ltrim(key, start, end);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			pool.put(con);
		}
	}
	
	public void publish(String channel, String message) {
		Connection con = pool.get();
		try {
			con.getClient().publish(channel, message);
		} catch (Exception e) {
			e.printStackTrace();
			con.reconnect();
			try {
				con.getClient().publish(channel, message);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			pool.put(con);
		}
	}
}
