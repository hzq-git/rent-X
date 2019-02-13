package priv.thinkam.rentx.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import priv.thinkam.rentx.common.base.BaseController;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.web.service.ItemService;
import priv.thinkam.rentx.web.service.StuffService;
import priv.thinkam.rentx.web.service.param.StuffParam;

import javax.annotation.Resource;

/**
 * 物品 controller
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Controller
@RequestMapping("/stuffs")
public class StuffController extends BaseController {
	@Resource
	private StuffService stuffService;
	@Resource
	private ItemService itemService;

	/**
	 * 开始租用
	 *
	 * <pre>
	 *     "不出租"的不显示
	 *     "已租"的显示应还日期
	 *     "未租"的显示租用操作
	 * </pre>
	 *
	 * @return page
	 */
	@GetMapping("/in")
	public String startRentIn(Model model) {
		model.addAttribute("stuffInVOList", stuffService.listStuffInVO());
		return "start_rent_in";
	}

	/**
	 * 开始出租
	 *
	 * @return page
	 */
	@GetMapping("/out/start")
	public String startRentOut() {
		return "start_rent_out";
	}

	/**
	 * 我的出租
	 *
	 * <pre>
	 *     "未租"的显示取消出租
	 * </pre>
	 *
	 * @return page
	 */
	@GetMapping("/out")
	public String myRentOut(Model model) {
		// 获取当前用户ID
		final int userId = 1;
		model.addAttribute("stuffOutVOList", stuffService.listStuffOutVO(userId));
		return "my_rent_out";
	}

	/**
	 * 开始出租提交
	 *
	 * @param stuffParam stuffParam
	 * @return Response
	 */
	@ResponseBody
	@PostMapping("/out")
	public Response addRentOut(@RequestBody StuffParam stuffParam) {
		// 获取当前用户ID
		final int userId = 1;
		stuffParam.setUserId(userId);
		log.info("开始出租提交stuffParam: {}", stuffParam);
		return stuffService.add(stuffParam);
	}

	/**
	 * 取消出租
	 *
	 * @param id stuff id
	 * @return Response
	 */
	@ResponseBody
	@PostMapping("/{id}/cancel-rent")
	public Response cancelRent(@PathVariable Integer id) {
		// 获取当前用户ID
		final int userId = 1;
		return stuffService.cancelRent(id, userId);
	}

	/**
	 * 租用
	 *
	 * @param id stuff id
	 * @param rentDay rent day
	 * @return Response
	 */
	@ResponseBody
	@PostMapping("/{id}/rent")
	public Response rent(@PathVariable Integer id, Integer rentDay) {
		// 获取当前用户ID
		final int userId = 3;
		return itemService.rent(id, userId, rentDay);
	}
}
