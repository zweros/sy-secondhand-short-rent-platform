import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 从数据库获取数据
 * @author Administrator
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private DataSource dataSource;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String sql="select password,roleName from t_user where username=?";

		String password="";
		String roleName="";

		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			prep = conn.prepareStatement(sql);
			prep.setObject(0,username);
			rs = prep.executeQuery();

			password = rs.getString("password");
			roleName=rs.getString("roleName");

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prep!=null){
				try {
					prep.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 权限使用AuthorityUtils将字符串转换为权限对象，用“，”号格隔开
		 */
		return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+roleName));
	}

}
