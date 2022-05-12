import React from "react";

import { Link, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import {
  ActionIcon,
  Avatar,
  Button,
  Group,
  Header,
  Menu,
  Text,
  useMantineColorScheme,
} from "@mantine/core";
import {
  IconLogout,
  IconMoonStars,
  IconShoppingCart,
  IconSun,
  IconUser,
} from "@tabler/icons";

import { useAuth0 } from "@auth0/auth0-react";

import CartDrawer from "./CartDrawer";
import LoginButton from "./LoginButton";
import * as userSelectors from "../store/shared/user.selectors";

export default function MyNavbar() {
  return (
    <Header height={75} p="md">
      <Group position="apart">
        <Group spacing="sm">
          <NavbarLinks />
        </Group>
        <Group spacing="sm">
          <ThemeToggle />
          <CartToggle />
          <LoginButton />
          <UserProfile />
        </Group>
      </Group>
    </Header>
  );
}

function CartToggle() {
  const [cartDrawerOpen, setCartDrawerOpen] = React.useState(false);
  const userPermissions = useSelector(userSelectors.permissions);

  if (!userPermissions.includes("buy:article")) {
    return null;
  }

  return (
    <>
      <ActionIcon
        size="lg"
        variant="outline"
        title="Open Shopping Cart"
        color="orange"
        onClick={() => setCartDrawerOpen(true)}
      >
        <IconShoppingCart />{" "}
      </ActionIcon>
      <CartDrawer
        opened={cartDrawerOpen}
        onClose={() => setCartDrawerOpen(false)}
      />
    </>
  );
}

function ThemeToggle() {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";

  return (
    <ActionIcon
      size="lg"
      variant="outline"
      color={dark ? "yellow" : "blue"}
      onClick={() => toggleColorScheme()}
      title="Toggle color scheme"
    >
      {dark ? <IconSun size={20} /> : <IconMoonStars size={20} />}
    </ActionIcon>
  );
}

function NavbarLinks() {
  const { isAuthenticated } = useAuth0();
  const userPermissions = useSelector(userSelectors.permissions);

  return (
    <>
      <Text component={Link} to="/" color="blue">
        Home
      </Text>
      {isAuthenticated && (
        <>
          {userPermissions.includes("buy:article") && (
            <Text component={Link} to="/my-articles" color="blue">
              My Articles
            </Text>
          )}
          {userPermissions.includes("create:category") && (
            <Text component={Link} to="/categories" color="blue">
              Categories
            </Text>
          )}
          {userPermissions.includes("create:developer") && (
            <Text component={Link} to="/developers" color="blue">
              Developers
            </Text>
          )}
          {userPermissions.includes("create:genre") && (
            <Text component={Link} to="/genres" color="blue">
              Genres
            </Text>
          )}
          {userPermissions.includes("create:publisher") && (
            <Text component={Link} to="/publishers" color="blue">
              Publishers
            </Text>
          )}
        </>
      )}
    </>
  );
}

function UserProfile() {
  const { isAuthenticated, user, logout } = useAuth0();
  const navigate = useNavigate();

  if (!isAuthenticated) {
    return null;
  }

  return (
    <Menu
      control={
        <Button px={5}>
          <Avatar src={user?.picture} radius="xl" mr={5} size={30} />{" "}
          {user?.name}
        </Button>
      }
    >
      <Menu.Item icon={<IconUser />} onClick={() => navigate("/profile")}>
        Profile
      </Menu.Item>
      <Menu.Item
        icon={<IconLogout />}
        onClick={() => {
          localStorage.removeItem("access_token");
          logout({ returnTo: window.location.origin });
        }}
      >
        Log Out
      </Menu.Item>
    </Menu>
  );
}
